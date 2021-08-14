package br.com.professorisidro.isilanguage.ast;

import java.util.ArrayList;

public class CommandRepeticao extends AbstractCommand {
    private String condition;
    private ArrayList<AbstractCommand> commandList;

    public CommandRepeticao(String condition, ArrayList<AbstractCommand> cl) {
        this.condition = condition;
        this.commandList = cl;
    }

    @Override
    public String generateJavaCode() {
        StringBuilder str = new StringBuilder();
        str.append("while (" + condition.replaceAll(" ou ", " || ").replaceAll(" e ", " && ") + ") {\n");
        for (AbstractCommand cmd : commandList) {
            str.append("\t" + cmd.generateJavaCode());
        }
        str.append("}\n");
        return str.toString();
    }

    @Override
    public String toString() {
        return "CommandRepeticao [condition=" + condition + ", commandList=" + commandList + "]";
    }
}
