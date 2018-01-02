package me.huding.grammar;

import java.io.File;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.antlr.v4.Tool;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class Grammar {
	
private static final Logger LOGGER = Logger.getLogger(Grammar.class.getName());
	
    public static final String PREFIX = "/src/main/java/";
	
	public static final String USER_DIR = System.getProperty("user.dir");
	
	public static final String SRC_PATH = USER_DIR + PREFIX;
	
	public static final String BLANK = " ";
	
	public static final String OP_PACKAGE ="-package";
	
	public static final String OP_O = "-o";
	
	public static final String OP_VISITOR = "-visitor";
	
	public static void gen(String grammarPath,String packageName) throws RuntimeException {
		StringBuilder builder = new StringBuilder();
		builder.append(grammarPath).append(BLANK);
		if(!isBlank(packageName)){
			builder.append(OP_PACKAGE).append(BLANK);
			builder.append(packageName).append(BLANK);
			String o = SRC_PATH + packageName.replaceAll("\\.", "/");
			File dir = new File(o);
			if(!dir.exists()){
				dir.mkdirs();
			}
			builder.append(OP_O).append(BLANK);
			builder.append(o).append(BLANK);
		}
		builder.append(OP_VISITOR);
		LOGGER.log(Level.INFO, builder.toString());
		try {
			Method mainMethod = Tool.class.getMethod("main", String[].class);
			mainMethod.invoke(null,(Object)builder.toString().split(BLANK));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static boolean isBlank(String string){
		if(string == null || string.trim().length() == 0){
			return true;
		}
		return false;
	}
	
	
	public static ParseTree parse(String lexerClassName,String parserClassName,String fileName,String startRuleName) throws Exception {
		CharStream stream = CharStreams.fromFileName(fileName);
		
		Class<?> lexerClass = Class.forName(lexerClassName);
		Lexer lexer = (Lexer) lexerClass.getConstructor(CharStream.class).newInstance(stream);
		
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		
		Class<?> parserClass = Class.forName(parserClassName);
		Parser parser = (Parser) parserClass.getConstructor(TokenStream.class).newInstance(tokens);
		Method method = parserClass.getMethod(startRuleName);
		ParseTree tree = (ParseTree) method.invoke(parser);
		return tree;
	}

}
