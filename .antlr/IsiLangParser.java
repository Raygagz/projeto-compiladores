// Generated from /home/pedro/Documents/IsiLanguageEmbriao/IsiLang.g4 by ANTLR 4.8

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

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class IsiLangParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		AP=10, FP=11, SC=12, OP=13, OPNUM=14, ATTR=15, VIR=16, ACH=17, FCH=18, 
		OPREL=19, OPRELNUM=20, OPLOG=21, ID=22, NUMBER=23, TEXT=24, WS=25;
	public static final int
		RULE_prog = 0, RULE_decl = 1, RULE_declaravar = 2, RULE_tipo = 3, RULE_bloco = 4, 
		RULE_cmd = 5, RULE_cmdleitura = 6, RULE_cmdescrita = 7, RULE_cmdattrib = 8, 
		RULE_cmdselecao = 9, RULE_cmdrepeticao = 10, RULE_expr = 11, RULE_termo = 12;
	private static String[] makeRuleNames() {
		return new String[] {
			"prog", "decl", "declaravar", "tipo", "bloco", "cmd", "cmdleitura", "cmdescrita", 
			"cmdattrib", "cmdselecao", "cmdrepeticao", "expr", "termo"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'programa'", "'fimprog;'", "'numero'", "'texto'", "'leia'", "'escreva'", 
			"'se'", "'senao'", "'enquanto'", "'('", "')'", "';'", "'+'", null, "'='", 
			"','", "'{'", "'}'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, "AP", "FP", 
			"SC", "OP", "OPNUM", "ATTR", "VIR", "ACH", "FCH", "OPREL", "OPRELNUM", 
			"OPLOG", "ID", "NUMBER", "TEXT", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "IsiLang.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


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

		public void verificaOperadorValido(int tipoOp, int tipoExpr){
			if (tipoOp != tipoExpr) {
				throw new IsiSemanticException("This operator is cannot be applied to the desired type");
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

	public IsiLangParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ProgContext extends ParserRuleContext {
		public DeclContext decl() {
			return getRuleContext(DeclContext.class,0);
		}
		public BlocoContext bloco() {
			return getRuleContext(BlocoContext.class,0);
		}
		public ProgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prog; }
	}

	public final ProgContext prog() throws RecognitionException {
		ProgContext _localctx = new ProgContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_prog);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(26);
			match(T__0);
			setState(27);
			decl();
			setState(28);
			bloco();
			setState(29);
			match(T__1);

			        program.setVarTable(symbolTable);
			        program.setComandos(stack.pop());
			    
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclContext extends ParserRuleContext {
		public List<DeclaravarContext> declaravar() {
			return getRuleContexts(DeclaravarContext.class);
		}
		public DeclaravarContext declaravar(int i) {
			return getRuleContext(DeclaravarContext.class,i);
		}
		public DeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decl; }
	}

	public final DeclContext decl() throws RecognitionException {
		DeclContext _localctx = new DeclContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_decl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(33); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(32);
				declaravar();
				}
				}
				setState(35); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__2 || _la==T__3 );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclaravarContext extends ParserRuleContext {
		public TipoContext tipo() {
			return getRuleContext(TipoContext.class,0);
		}
		public List<TerminalNode> ID() { return getTokens(IsiLangParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(IsiLangParser.ID, i);
		}
		public TerminalNode SC() { return getToken(IsiLangParser.SC, 0); }
		public List<TerminalNode> VIR() { return getTokens(IsiLangParser.VIR); }
		public TerminalNode VIR(int i) {
			return getToken(IsiLangParser.VIR, i);
		}
		public DeclaravarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaravar; }
	}

	public final DeclaravarContext declaravar() throws RecognitionException {
		DeclaravarContext _localctx = new DeclaravarContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_declaravar);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(37);
			tipo();
			setState(38);
			match(ID);

			        _varName = _input.LT(-1).getText();
			        _varValue = null;
			        symbol = new IsiVariable(_varName, _tipo, _varValue);
			        if (!symbolTable.exists(_varName)){
			            symbolTable.add(symbol);
			        }
			        else{
			            throw new IsiSemanticException("Symbol "+_varName+" already declared");
			        }
			    
			setState(45);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==VIR) {
				{
				{
				setState(40);
				match(VIR);
				setState(41);
				match(ID);

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
				}
				setState(47);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(48);
			match(SC);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TipoContext extends ParserRuleContext {
		public TipoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tipo; }
	}

	public final TipoContext tipo() throws RecognitionException {
		TipoContext _localctx = new TipoContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_tipo);
		try {
			setState(54);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__2:
				enterOuterAlt(_localctx, 1);
				{
				setState(50);
				match(T__2);
				 _tipo = IsiVariable.NUMBER;  
				}
				break;
			case T__3:
				enterOuterAlt(_localctx, 2);
				{
				setState(52);
				match(T__3);
				 _tipo = IsiVariable.TEXT;  
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BlocoContext extends ParserRuleContext {
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public BlocoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bloco; }
	}

	public final BlocoContext bloco() throws RecognitionException {
		BlocoContext _localctx = new BlocoContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_bloco);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			 stack.push(new ArrayList<AbstractCommand>()); 
			setState(58); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(57);
				cmd();
				}
				}
				setState(60); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__8) | (1L << ID))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdContext extends ParserRuleContext {
		public CmdleituraContext cmdleitura() {
			return getRuleContext(CmdleituraContext.class,0);
		}
		public CmdescritaContext cmdescrita() {
			return getRuleContext(CmdescritaContext.class,0);
		}
		public CmdattribContext cmdattrib() {
			return getRuleContext(CmdattribContext.class,0);
		}
		public CmdselecaoContext cmdselecao() {
			return getRuleContext(CmdselecaoContext.class,0);
		}
		public CmdrepeticaoContext cmdrepeticao() {
			return getRuleContext(CmdrepeticaoContext.class,0);
		}
		public CmdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmd; }
	}

	public final CmdContext cmd() throws RecognitionException {
		CmdContext _localctx = new CmdContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_cmd);
		try {
			setState(67);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__4:
				enterOuterAlt(_localctx, 1);
				{
				setState(62);
				cmdleitura();
				}
				break;
			case T__5:
				enterOuterAlt(_localctx, 2);
				{
				setState(63);
				cmdescrita();
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 3);
				{
				setState(64);
				cmdattrib();
				}
				break;
			case T__6:
				enterOuterAlt(_localctx, 4);
				{
				setState(65);
				cmdselecao();
				}
				break;
			case T__8:
				enterOuterAlt(_localctx, 5);
				{
				setState(66);
				cmdrepeticao();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdleituraContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(IsiLangParser.AP, 0); }
		public TerminalNode ID() { return getToken(IsiLangParser.ID, 0); }
		public TerminalNode FP() { return getToken(IsiLangParser.FP, 0); }
		public TerminalNode SC() { return getToken(IsiLangParser.SC, 0); }
		public CmdleituraContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdleitura; }
	}

	public final CmdleituraContext cmdleitura() throws RecognitionException {
		CmdleituraContext _localctx = new CmdleituraContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_cmdleitura);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(69);
			match(T__4);
			setState(70);
			match(AP);
			setState(71);
			match(ID);
			 verificaID(_input.LT(-1).getText());
			                _readID = _input.LT(-1).getText();
			            
			setState(73);
			match(FP);
			setState(74);
			match(SC);

			    IsiVariable var = (IsiVariable)symbolTable.get(_readID);
			    CommandLeitura cmd = new CommandLeitura(_readID, var);
			    stack.peek().add(cmd);
			    
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdescritaContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(IsiLangParser.AP, 0); }
		public TermoContext termo() {
			return getRuleContext(TermoContext.class,0);
		}
		public TerminalNode FP() { return getToken(IsiLangParser.FP, 0); }
		public TerminalNode SC() { return getToken(IsiLangParser.SC, 0); }
		public CmdescritaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdescrita; }
	}

	public final CmdescritaContext cmdescrita() throws RecognitionException {
		CmdescritaContext _localctx = new CmdescritaContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_cmdescrita);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(77);
			match(T__5);
			setState(78);
			match(AP);
			setState(79);
			termo();

			            _writeID = _input.LT(-1).getText();
			            
			setState(81);
			match(FP);
			setState(82);
			match(SC);

			        CommandEscrita cmd = new CommandEscrita(_writeID);
			        stack.peek().add(cmd);
			    
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdattribContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(IsiLangParser.ID, 0); }
		public TerminalNode ATTR() { return getToken(IsiLangParser.ATTR, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode SC() { return getToken(IsiLangParser.SC, 0); }
		public CmdattribContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdattrib; }
	}

	public final CmdattribContext cmdattrib() throws RecognitionException {
		CmdattribContext _localctx = new CmdattribContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_cmdattrib);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(85);
			match(ID);
			 verificaID(_input.LT(-1).getText());
			        _exprID = _input.LT(-1).getText();
					_exprTipo = getTypeByID(_input.LT(-1).getText());
			        
			setState(87);
			match(ATTR);
			 _exprContent = ""; 
			setState(89);
			expr();
			setState(90);
			match(SC);

			        CommandAtribuicao cmd = new CommandAtribuicao(_exprID, _exprContent);
			        stack.peek().add(cmd);
			    
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdselecaoContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(IsiLangParser.AP, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode FP() { return getToken(IsiLangParser.FP, 0); }
		public List<TerminalNode> ACH() { return getTokens(IsiLangParser.ACH); }
		public TerminalNode ACH(int i) {
			return getToken(IsiLangParser.ACH, i);
		}
		public List<TerminalNode> FCH() { return getTokens(IsiLangParser.FCH); }
		public TerminalNode FCH(int i) {
			return getToken(IsiLangParser.FCH, i);
		}
		public List<TerminalNode> OPRELNUM() { return getTokens(IsiLangParser.OPRELNUM); }
		public TerminalNode OPRELNUM(int i) {
			return getToken(IsiLangParser.OPRELNUM, i);
		}
		public List<TerminalNode> OPREL() { return getTokens(IsiLangParser.OPREL); }
		public TerminalNode OPREL(int i) {
			return getToken(IsiLangParser.OPREL, i);
		}
		public List<TerminalNode> OPLOG() { return getTokens(IsiLangParser.OPLOG); }
		public TerminalNode OPLOG(int i) {
			return getToken(IsiLangParser.OPLOG, i);
		}
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public CmdselecaoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdselecao; }
	}

	public final CmdselecaoContext cmdselecao() throws RecognitionException {
		CmdselecaoContext _localctx = new CmdselecaoContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_cmdselecao);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(93);
			match(T__6);
			setState(94);
			match(AP);
			 _exprTipo = IsiVariable.DONTCARE; 
			setState(96);
			expr();
			 _exprDecision = _exprContent;
					
			setState(101);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case OPRELNUM:
				{
				setState(98);
				match(OPRELNUM);
				 verificaOperadorValido(IsiVariable.NUMBER, _exprTipo);
				}
				break;
			case OPREL:
				{
				setState(100);
				match(OPREL);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			 _exprDecision += _input.LT(-1).getText();
					
			setState(104);
			expr();
			_exprDecision += _exprContent; 
			setState(121);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OPLOG) {
				{
				{
				setState(106);
				match(OPLOG);
				 _exprTipo = IsiVariable.DONTCARE; _exprDecision += " " + _input.LT(-1).getText() + " ";
							
				setState(108);
				expr();
				 _exprDecision += _input.LT(-1).getText(); 
						
				setState(113);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case OPRELNUM:
					{
					setState(110);
					match(OPRELNUM);
					 verificaOperadorValido(IsiVariable.NUMBER, _exprTipo); 
					}
					break;
				case OPREL:
					{
					setState(112);
					match(OPREL);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				 _exprDecision += _input.LT(-1).getText(); 
						
				setState(116);
				expr();
				 _exprDecision += _input.LT(-1).getText(); 
				}
				}
				setState(123);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(124);
			match(FP);
			setState(125);
			match(ACH);
			stack.push(new ArrayList<AbstractCommand>());
			    
			setState(128); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(127);
				cmd();
				}
				}
				setState(130); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__8) | (1L << ID))) != 0) );
			setState(132);
			match(FCH);

			        listaTrue = stack.pop();
					listaFalse = new ArrayList<AbstractCommand>();
			    
			setState(145);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__7) {
				{
				setState(134);
				match(T__7);
				setState(135);
				match(ACH);

				        stack.push(new ArrayList<AbstractCommand>());
				        
				{
				setState(138); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(137);
					cmd();
					}
					}
					setState(140); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__8) | (1L << ID))) != 0) );
				}
				setState(142);
				match(FCH);

				        listaFalse = stack.pop();
				    
				}
			}

			CommandDecisao cmd = new CommandDecisao(_exprDecision, listaTrue, listaFalse);
			        stack.peek().add(cmd);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdrepeticaoContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(IsiLangParser.AP, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode FP() { return getToken(IsiLangParser.FP, 0); }
		public TerminalNode ACH() { return getToken(IsiLangParser.ACH, 0); }
		public TerminalNode FCH() { return getToken(IsiLangParser.FCH, 0); }
		public List<TerminalNode> OPRELNUM() { return getTokens(IsiLangParser.OPRELNUM); }
		public TerminalNode OPRELNUM(int i) {
			return getToken(IsiLangParser.OPRELNUM, i);
		}
		public List<TerminalNode> OPREL() { return getTokens(IsiLangParser.OPREL); }
		public TerminalNode OPREL(int i) {
			return getToken(IsiLangParser.OPREL, i);
		}
		public List<TerminalNode> OPLOG() { return getTokens(IsiLangParser.OPLOG); }
		public TerminalNode OPLOG(int i) {
			return getToken(IsiLangParser.OPLOG, i);
		}
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public CmdrepeticaoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdrepeticao; }
	}

	public final CmdrepeticaoContext cmdrepeticao() throws RecognitionException {
		CmdrepeticaoContext _localctx = new CmdrepeticaoContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_cmdrepeticao);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(149);
			match(T__8);
			setState(150);
			match(AP);
			 _exprTipo = IsiVariable.DONTCARE; 
			setState(152);
			expr();
			 _exprDecision = _input.LT(-1).getText(); 
					
			setState(157);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case OPRELNUM:
				{
				setState(154);
				match(OPRELNUM);
				 verificaOperadorValido(IsiVariable.NUMBER, _exprTipo); 
				}
				break;
			case OPREL:
				{
				setState(156);
				match(OPREL);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			 _exprDecision += _input.LT(-1).getText(); 
					
			setState(160);
			expr();
			 _exprDecision += _input.LT(-1).getText(); 
			setState(177);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OPLOG) {
				{
				{
				setState(162);
				match(OPLOG);
				 _exprTipo = IsiVariable.DONTCARE; _exprDecision += " " + _input.LT(-1).getText() + " ";
							
				setState(164);
				expr();
				 _exprDecision += _input.LT(-1).getText(); 
						
				setState(169);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case OPRELNUM:
					{
					setState(166);
					match(OPRELNUM);
					 verificaOperadorValido(IsiVariable.NUMBER, _exprTipo); 
					}
					break;
				case OPREL:
					{
					setState(168);
					match(OPREL);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				 _exprDecision += _input.LT(-1).getText(); 
						
				setState(172);
				expr();
				 _exprDecision += _input.LT(-1).getText(); 
				}
				}
				setState(179);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(180);
			match(FP);
			setState(181);
			match(ACH);
			 stack.push(new ArrayList<AbstractCommand>());
					
			{
			setState(184); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(183);
				cmd();
				}
				}
				setState(186); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__8) | (1L << ID))) != 0) );
			}
			setState(188);
			match(FCH);

						CommandRepeticao cmd = new CommandRepeticao(_exprDecision, stack.pop());
						stack.peek().add(cmd);
					
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprContext extends ParserRuleContext {
		public List<TermoContext> termo() {
			return getRuleContexts(TermoContext.class);
		}
		public TermoContext termo(int i) {
			return getRuleContext(TermoContext.class,i);
		}
		public List<TerminalNode> OPNUM() { return getTokens(IsiLangParser.OPNUM); }
		public TerminalNode OPNUM(int i) {
			return getToken(IsiLangParser.OPNUM, i);
		}
		public List<TerminalNode> OP() { return getTokens(IsiLangParser.OP); }
		public TerminalNode OP(int i) {
			return getToken(IsiLangParser.OP, i);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_expr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(191);
			termo();

					verificaTipo(_exprTipo, _tipo);
					_exprContent = _input.LT(-1).getText();
				
			setState(204);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OP || _la==OPNUM) {
				{
				{
				setState(196);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case OPNUM:
					{
					setState(193);
					match(OPNUM);
					 verificaOperadorValido(IsiVariable.NUMBER, _exprTipo); 
					}
					break;
				case OP:
					{
					setState(195);
					match(OP);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}

							_exprContent += _input.LT(-1).getText();
						
				setState(199);
				termo();
				 verificaTipo(_exprTipo, _tipo); _exprContent += _input.LT(-1).getText();
						
				}
				}
				setState(206);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TermoContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(IsiLangParser.ID, 0); }
		public TerminalNode NUMBER() { return getToken(IsiLangParser.NUMBER, 0); }
		public TerminalNode TEXT() { return getToken(IsiLangParser.TEXT, 0); }
		public TermoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_termo; }
	}

	public final TermoContext termo() throws RecognitionException {
		TermoContext _localctx = new TermoContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_termo);
		try {
			setState(213);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(207);
				match(ID);

				        verificaID(_input.LT(-1).getText());
						use(_input.LT(-1).getText());
						_tipo = getTypeByID(_input.LT(-1).getText());
				    
				}
				break;
			case NUMBER:
				enterOuterAlt(_localctx, 2);
				{
				setState(209);
				match(NUMBER);

						_tipo = IsiVariable.NUMBER;
				    
				}
				break;
			case TEXT:
				enterOuterAlt(_localctx, 3);
				{
				setState(211);
				match(TEXT);

						_tipo = IsiVariable.TEXT;
					
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\33\u00da\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\3\2\3\2\3\2\3\2\3\2\3\2\3\3\6\3$\n\3\r"+
		"\3\16\3%\3\4\3\4\3\4\3\4\3\4\3\4\7\4.\n\4\f\4\16\4\61\13\4\3\4\3\4\3\5"+
		"\3\5\3\5\3\5\5\59\n\5\3\6\3\6\6\6=\n\6\r\6\16\6>\3\7\3\7\3\7\3\7\3\7\5"+
		"\7F\n\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\5\13h\n\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\5\13"+
		"t\n\13\3\13\3\13\3\13\3\13\7\13z\n\13\f\13\16\13}\13\13\3\13\3\13\3\13"+
		"\3\13\6\13\u0083\n\13\r\13\16\13\u0084\3\13\3\13\3\13\3\13\3\13\3\13\6"+
		"\13\u008d\n\13\r\13\16\13\u008e\3\13\3\13\3\13\5\13\u0094\n\13\3\13\3"+
		"\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u00a0\n\f\3\f\3\f\3\f\3\f\3\f"+
		"\3\f\3\f\3\f\3\f\3\f\5\f\u00ac\n\f\3\f\3\f\3\f\3\f\7\f\u00b2\n\f\f\f\16"+
		"\f\u00b5\13\f\3\f\3\f\3\f\3\f\6\f\u00bb\n\f\r\f\16\f\u00bc\3\f\3\f\3\f"+
		"\3\r\3\r\3\r\3\r\3\r\5\r\u00c7\n\r\3\r\3\r\3\r\3\r\7\r\u00cd\n\r\f\r\16"+
		"\r\u00d0\13\r\3\16\3\16\3\16\3\16\3\16\3\16\5\16\u00d8\n\16\3\16\2\2\17"+
		"\2\4\6\b\n\f\16\20\22\24\26\30\32\2\2\2\u00e2\2\34\3\2\2\2\4#\3\2\2\2"+
		"\6\'\3\2\2\2\b8\3\2\2\2\n:\3\2\2\2\fE\3\2\2\2\16G\3\2\2\2\20O\3\2\2\2"+
		"\22W\3\2\2\2\24_\3\2\2\2\26\u0097\3\2\2\2\30\u00c1\3\2\2\2\32\u00d7\3"+
		"\2\2\2\34\35\7\3\2\2\35\36\5\4\3\2\36\37\5\n\6\2\37 \7\4\2\2 !\b\2\1\2"+
		"!\3\3\2\2\2\"$\5\6\4\2#\"\3\2\2\2$%\3\2\2\2%#\3\2\2\2%&\3\2\2\2&\5\3\2"+
		"\2\2\'(\5\b\5\2()\7\30\2\2)/\b\4\1\2*+\7\22\2\2+,\7\30\2\2,.\b\4\1\2-"+
		"*\3\2\2\2.\61\3\2\2\2/-\3\2\2\2/\60\3\2\2\2\60\62\3\2\2\2\61/\3\2\2\2"+
		"\62\63\7\16\2\2\63\7\3\2\2\2\64\65\7\5\2\2\659\b\5\1\2\66\67\7\6\2\2\67"+
		"9\b\5\1\28\64\3\2\2\28\66\3\2\2\29\t\3\2\2\2:<\b\6\1\2;=\5\f\7\2<;\3\2"+
		"\2\2=>\3\2\2\2><\3\2\2\2>?\3\2\2\2?\13\3\2\2\2@F\5\16\b\2AF\5\20\t\2B"+
		"F\5\22\n\2CF\5\24\13\2DF\5\26\f\2E@\3\2\2\2EA\3\2\2\2EB\3\2\2\2EC\3\2"+
		"\2\2ED\3\2\2\2F\r\3\2\2\2GH\7\7\2\2HI\7\f\2\2IJ\7\30\2\2JK\b\b\1\2KL\7"+
		"\r\2\2LM\7\16\2\2MN\b\b\1\2N\17\3\2\2\2OP\7\b\2\2PQ\7\f\2\2QR\5\32\16"+
		"\2RS\b\t\1\2ST\7\r\2\2TU\7\16\2\2UV\b\t\1\2V\21\3\2\2\2WX\7\30\2\2XY\b"+
		"\n\1\2YZ\7\21\2\2Z[\b\n\1\2[\\\5\30\r\2\\]\7\16\2\2]^\b\n\1\2^\23\3\2"+
		"\2\2_`\7\t\2\2`a\7\f\2\2ab\b\13\1\2bc\5\30\r\2cg\b\13\1\2de\7\26\2\2e"+
		"h\b\13\1\2fh\7\25\2\2gd\3\2\2\2gf\3\2\2\2hi\3\2\2\2ij\b\13\1\2jk\5\30"+
		"\r\2k{\b\13\1\2lm\7\27\2\2mn\b\13\1\2no\5\30\r\2os\b\13\1\2pq\7\26\2\2"+
		"qt\b\13\1\2rt\7\25\2\2sp\3\2\2\2sr\3\2\2\2tu\3\2\2\2uv\b\13\1\2vw\5\30"+
		"\r\2wx\b\13\1\2xz\3\2\2\2yl\3\2\2\2z}\3\2\2\2{y\3\2\2\2{|\3\2\2\2|~\3"+
		"\2\2\2}{\3\2\2\2~\177\7\r\2\2\177\u0080\7\23\2\2\u0080\u0082\b\13\1\2"+
		"\u0081\u0083\5\f\7\2\u0082\u0081\3\2\2\2\u0083\u0084\3\2\2\2\u0084\u0082"+
		"\3\2\2\2\u0084\u0085\3\2\2\2\u0085\u0086\3\2\2\2\u0086\u0087\7\24\2\2"+
		"\u0087\u0093\b\13\1\2\u0088\u0089\7\n\2\2\u0089\u008a\7\23\2\2\u008a\u008c"+
		"\b\13\1\2\u008b\u008d\5\f\7\2\u008c\u008b\3\2\2\2\u008d\u008e\3\2\2\2"+
		"\u008e\u008c\3\2\2\2\u008e\u008f\3\2\2\2\u008f\u0090\3\2\2\2\u0090\u0091"+
		"\7\24\2\2\u0091\u0092\b\13\1\2\u0092\u0094\3\2\2\2\u0093\u0088\3\2\2\2"+
		"\u0093\u0094\3\2\2\2\u0094\u0095\3\2\2\2\u0095\u0096\b\13\1\2\u0096\25"+
		"\3\2\2\2\u0097\u0098\7\13\2\2\u0098\u0099\7\f\2\2\u0099\u009a\b\f\1\2"+
		"\u009a\u009b\5\30\r\2\u009b\u009f\b\f\1\2\u009c\u009d\7\26\2\2\u009d\u00a0"+
		"\b\f\1\2\u009e\u00a0\7\25\2\2\u009f\u009c\3\2\2\2\u009f\u009e\3\2\2\2"+
		"\u00a0\u00a1\3\2\2\2\u00a1\u00a2\b\f\1\2\u00a2\u00a3\5\30\r\2\u00a3\u00b3"+
		"\b\f\1\2\u00a4\u00a5\7\27\2\2\u00a5\u00a6\b\f\1\2\u00a6\u00a7\5\30\r\2"+
		"\u00a7\u00ab\b\f\1\2\u00a8\u00a9\7\26\2\2\u00a9\u00ac\b\f\1\2\u00aa\u00ac"+
		"\7\25\2\2\u00ab\u00a8\3\2\2\2\u00ab\u00aa\3\2\2\2\u00ac\u00ad\3\2\2\2"+
		"\u00ad\u00ae\b\f\1\2\u00ae\u00af\5\30\r\2\u00af\u00b0\b\f\1\2\u00b0\u00b2"+
		"\3\2\2\2\u00b1\u00a4\3\2\2\2\u00b2\u00b5\3\2\2\2\u00b3\u00b1\3\2\2\2\u00b3"+
		"\u00b4\3\2\2\2\u00b4\u00b6\3\2\2\2\u00b5\u00b3\3\2\2\2\u00b6\u00b7\7\r"+
		"\2\2\u00b7\u00b8\7\23\2\2\u00b8\u00ba\b\f\1\2\u00b9\u00bb\5\f\7\2\u00ba"+
		"\u00b9\3\2\2\2\u00bb\u00bc\3\2\2\2\u00bc\u00ba\3\2\2\2\u00bc\u00bd\3\2"+
		"\2\2\u00bd\u00be\3\2\2\2\u00be\u00bf\7\24\2\2\u00bf\u00c0\b\f\1\2\u00c0"+
		"\27\3\2\2\2\u00c1\u00c2\5\32\16\2\u00c2\u00ce\b\r\1\2\u00c3\u00c4\7\20"+
		"\2\2\u00c4\u00c7\b\r\1\2\u00c5\u00c7\7\17\2\2\u00c6\u00c3\3\2\2\2\u00c6"+
		"\u00c5\3\2\2\2\u00c7\u00c8\3\2\2\2\u00c8\u00c9\b\r\1\2\u00c9\u00ca\5\32"+
		"\16\2\u00ca\u00cb\b\r\1\2\u00cb\u00cd\3\2\2\2\u00cc\u00c6\3\2\2\2\u00cd"+
		"\u00d0\3\2\2\2\u00ce\u00cc\3\2\2\2\u00ce\u00cf\3\2\2\2\u00cf\31\3\2\2"+
		"\2\u00d0\u00ce\3\2\2\2\u00d1\u00d2\7\30\2\2\u00d2\u00d8\b\16\1\2\u00d3"+
		"\u00d4\7\31\2\2\u00d4\u00d8\b\16\1\2\u00d5\u00d6\7\32\2\2\u00d6\u00d8"+
		"\b\16\1\2\u00d7\u00d1\3\2\2\2\u00d7\u00d3\3\2\2\2\u00d7\u00d5\3\2\2\2"+
		"\u00d8\33\3\2\2\2\24%/8>Egs{\u0084\u008e\u0093\u009f\u00ab\u00b3\u00bc"+
		"\u00c6\u00ce\u00d7";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}