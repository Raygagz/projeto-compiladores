grammar IsiLang;

@header {
	import br.com.professorisidro.isilanguage.datastructures.IsiSymbol;
	import br.com.professorisidro.isilanguage.datastructures.IsiVariable;
	import br.com.professorisidro.isilanguage.datastructures.IsiSymbolTable;
	import br.com.professorisidro.isilanguage.exceptions.IsiSemanticException;
	import br.com.professorisidro.isilanguage.ast.IsiProgram;
	import br.com.professorisidro.isilanguage.ast.AbstractCommand;
	import br.com.professorisidro.isilanguage.ast.CommandLeitura;
	import br.com.professorisidro.isilanguage.ast.CommandEscrita;
	import br.com.professorisidro.isilanguage.ast.CommandAtribuicao;
	import br.com.professorisidro.isilanguage.ast.CommandDecisao;
	import br.com.professorisidro.isilanguage.ast.CommandRepeticao;
	import java.util.ArrayList;
	import java.util.Stack;
}

@members {
	private int _tipo;
	private int _exprTipo;
	private String _varName;
	private String _varValue;
	private IsiSymbolTable symbolTable = new IsiSymbolTable();
	private IsiSymbol symbol;
	private IsiProgram program = new IsiProgram();
	private Stack<ArrayList<AbstractCommand>> stack = new Stack<ArrayList<AbstractCommand>>();
	private String _readID;
	private String _writeID;
	private String _exprID;
	private String _exprContent;
	private String _exprDecision;
	private ArrayList<AbstractCommand> listaTrue;
	private ArrayList<AbstractCommand> listaFalse;

	public void verificaID(String id){
		if (!symbolTable.exists(id)){
			throw new IsiSemanticException("Symbol "+id+" not declared");
		}
	}

	public void verificaTipo(int tipo1, int tipo2){
		if (tipo1 == IsiVariable.DONTCARE || tipo2 == IsiVariable.DONTCARE) {
			_exprTipo = _tipo;
		}
		else if (tipo1 != tipo2) {
			throw new IsiSemanticException("Cannot perform operation, types do not match");
		}
	}

	public int getTypeByID(String id){
		return symbolTable.get(id).getType();
	}

	public void use(String id){
		if (symbolTable.exists(id)) {
			symbolTable.get(id).use();
		}
	}

	public void exibeComandos(){
		for (AbstractCommand c: program.getComandos()){
			System.out.println(c);
		}
	}

	public void variaveisForamUsadas(){
		for (IsiSymbol s: symbolTable.getAll()){
			if (!s.isUsed()) {
				System.out.println("Warning: symbol " + s + " is not used.");
			}
		}
	}

	public void generateCode(){
		program.generateTarget();
	}
}

prog:
	'programa' decl bloco 'fimprog;' {
        program.setVarTable(symbolTable);
        program.setComandos(stack.pop());
    };

decl: (declaravar)+;

declaravar:
	tipo ID {
        _varName = _input.LT(-1).getText();
        _varValue = null;
        symbol = new IsiVariable(_varName, _tipo, _varValue);
        if (!symbolTable.exists(_varName)){
            symbolTable.add(symbol);
        }
        else{
            throw new IsiSemanticException("Symbol "+_varName+" already declared");
        }
    } (
		VIR ID {
                _varName = _input.LT(-1).getText();
                _varValue = null;
                symbol = new IsiVariable(_varName, _tipo, _varValue);
                if (!symbolTable.exists(_varName)){
                    symbolTable.add(symbol);
                }
                else{
                    throw new IsiSemanticException("Symbol "+_varName+" already declared");
                }
            }
	)* SC;

tipo:
	'numero' { _tipo = IsiVariable.NUMBER;  }
	| 'texto' { _tipo = IsiVariable.TEXT;  };

bloco: { stack.push(new ArrayList<AbstractCommand>()); } (cmd)+;

cmd:
	cmdleitura
	| cmdescrita
	| cmdattrib
	| cmdselecao
	| cmdrepeticao;

cmdleitura:
	'leia' AP ID { verificaID(_input.LT(-1).getText());
                _readID = _input.LT(-1).getText();
            } FP SC {
    IsiVariable var = (IsiVariable)symbolTable.get(_readID);
    CommandLeitura cmd = new CommandLeitura(_readID, var);
    stack.peek().add(cmd);
    };

cmdescrita:
	'escreva' AP termo {
            _writeID = _input.LT(-1).getText();
            } FP SC {
		use(_input.LT(-1).getText());
        CommandEscrita cmd = new CommandEscrita(_writeID);
        stack.peek().add(cmd);
    };

cmdattrib:
	ID { verificaID(_input.LT(-1).getText());
        _exprID = _input.LT(-1).getText();
		_exprTipo = getTypeByID(_input.LT(-1).getText());
        } ATTR { _exprContent = ""; } expr SC {
        CommandAtribuicao cmd = new CommandAtribuicao(_exprID, _exprContent);
        stack.peek().add(cmd);
    };

cmdselecao:
	'se' AP { _exprTipo = IsiVariable.DONTCARE; } expr { _exprDecision = _input.LT(-1).getText(); 
		} OPREL { _exprDecision += _input.LT(-1).getText(); 
		} expr {_exprDecision += _input.LT(-1).getText(); } FP ACH {stack.push(new ArrayList<AbstractCommand>());
    } (cmd)+ FCH {
        listaTrue = stack.pop();
		listaFalse = new ArrayList<AbstractCommand>();
    } (
		'senao' ACH {
        stack.push(new ArrayList<AbstractCommand>());
        } (cmd+) FCH {
        listaFalse = stack.pop();
    }
	)? {CommandDecisao cmd = new CommandDecisao(_exprDecision, listaTrue, listaFalse);
        stack.peek().add(cmd);};

cmdrepeticao:
	'enquanto' AP { _exprTipo = IsiVariable.DONTCARE; } expr { _exprDecision = _input.LT(-1).getText(); 
		} OPREL { _exprDecision += _input.LT(-1).getText(); 
		} expr { _exprDecision += _input.LT(-1).getText(); } FP ACH { stack.push(new ArrayList<AbstractCommand>());
		} (cmd+) FCH {
			CommandRepeticao cmd = new CommandRepeticao(_exprDecision, stack.pop());
			stack.peek().add(cmd);
		};

expr:
	termo {
		verificaTipo(_exprTipo, _tipo);
	} (
		(
			OPNUM { verificaTipo(IsiVariable.NUMBER, _exprTipo); }
			| OP
		) {
			_exprContent += _input.LT(-1).getText();
		} termo { verificaTipo(_exprTipo, _tipo);
		}
	)*;

termo:
	ID {
        verificaID(_input.LT(-1).getText());
		use(_input.LT(-1).getText());
        _exprContent += _input.LT(-1).getText();
		_tipo = getTypeByID(_input.LT(-1).getText());
    }
	| NUMBER {
        _exprContent += _input.LT(-1).getText();
		_tipo = IsiVariable.NUMBER;
    }
	| TEXT {
		_exprContent += _input.LT(-1).getText();
		_tipo = IsiVariable.TEXT;
	};

AP: '(';

FP: ')';

SC: ';';

OP: '+';

OPNUM: '-' | '*' | '/';

ATTR: '=';

VIR: ',';

ACH: '{';

FCH: '}';

OPREL: '>' | '<' | '>=' | '<=' | '==' | '!=';

ID: [a-z] ([a-z] | [A-Z] | [0-9])*;

NUMBER: [0-9]+ ('.' [0-9]+)?;

TEXT: '"' .*? '"';

WS: (' ' | '\t' | '\n' | '\r') -> skip;