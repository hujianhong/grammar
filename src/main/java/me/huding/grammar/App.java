package me.huding.grammar;

import me.huding.grammar.java8.DuangJava8Listener;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.File;

/**
 * Hello world!
 *
 */
public class App {
	
	
	public static void main(String[] args) {
//		String grammarName = "Java8";
//		String grammarName = "CPP14";
//		String grammarName = "Python3";
//		generate(grammarName);
		
		String grammarName = "Java8";
//		String fileName = "item.java";
		String startRuleName = "compilationUnit";
		File dir = new File("sources");
		System.out.println(System.getProperty("user.dir"));
		System.out.println(App.class.getResource("/"));
		for(File file : dir.listFiles()){
			System.out.println("------------------"+ file.getName() + "-----------------------");
			ParseTreeListener listener = new DuangJava8Listener();
			walk(grammarName, file.getAbsolutePath(), startRuleName, listener);
		}
	}
	
	
	public static final String PREFIX = IConst.PKG_PREFIX + IConst.DOT;
	
	public static void generate(String grammarName){
		String grammarPath = App.class.getResource("/").getPath() + grammarName + ".g4";
		String packageName = PREFIX + grammarName.toLowerCase();
		Grammar.gen(grammarPath, packageName);
	}
	
	public static void generate(String grammarPath,String grammarName){
		String packageName = PREFIX + grammarName.toLowerCase();
		Grammar.gen(grammarPath, packageName);
	}
	
	public static void walk(String grammarName,String fileName,String startRuleName,ParseTreeListener listener){
		try {
			String lexerClassName = getLexerClassName(grammarName);
			String parserClassName = getParserClassName(grammarName);
			ParseTree tree = Grammar.parse(lexerClassName, parserClassName, fileName, startRuleName);
			ParseTreeWalker walker = new ParseTreeWalker();
			walker.walk(listener, tree);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static String getLexerClassName(String grammarName) {
		return PREFIX + grammarName.toLowerCase() + IConst.DOT + grammarName + "Lexer";
	}
	
	public static String getParserClassName(String grammarName) {
		return PREFIX + grammarName.toLowerCase() + IConst.DOT + grammarName + "Parser";
	}
}
