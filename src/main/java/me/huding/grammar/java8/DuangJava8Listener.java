package me.huding.grammar.java8;


import java.util.ArrayList;
import java.util.List;

import me.huding.grammar.java8.Java8Parser.ClassModifierContext;
import me.huding.grammar.java8.Java8Parser.ConstructorDeclarationContext;
import me.huding.grammar.java8.Java8Parser.FieldDeclarationContext;
import me.huding.grammar.java8.Java8Parser.FieldModifierContext;
import me.huding.grammar.java8.Java8Parser.FormalParameterContext;
import me.huding.grammar.java8.Java8Parser.FormalParameterListContext;
import me.huding.grammar.java8.Java8Parser.FormalParametersContext;
import me.huding.grammar.java8.Java8Parser.LastFormalParameterContext;
import me.huding.grammar.java8.Java8Parser.MethodDeclarationContext;
import me.huding.grammar.java8.Java8Parser.MethodDeclaratorContext;
import me.huding.grammar.java8.Java8Parser.MethodHeaderContext;
import me.huding.grammar.java8.Java8Parser.MethodModifierContext;
import me.huding.grammar.java8.Java8Parser.NormalClassDeclarationContext;
import me.huding.grammar.java8.Java8Parser.PackageDeclarationContext;
import me.huding.grammar.java8.Java8Parser.ReceiverParameterContext;
import me.huding.grammar.java8.Java8Parser.SingleTypeImportDeclarationContext;

public class DuangJava8Listener extends Java8BaseListener {
	
	private String packageName;
	
	@Override
	public void enterNormalClassDeclaration(NormalClassDeclarationContext ctx){
		super.enterNormalClassDeclaration(ctx);
		StringBuilder builder = new StringBuilder();
		for(ClassModifierContext cmCtx : ctx.classModifier()){
			builder.append(cmCtx.getText()).append(" ");
		}
		builder.append(ctx.Identifier());
		System.out.println(builder.toString());
	}
	
	@Override
	public void enterPackageDeclaration(PackageDeclarationContext ctx){
		super.enterPackageDeclaration(ctx);
		if(ctx.getChildCount() == 0){
			this.packageName = "";
		}  else {
			StringBuilder builder = new StringBuilder();
			builder.append(ctx.getChild(0).getText()).append(" ");
			for(int i = 1;i < ctx.getChildCount();i ++){
				builder.append(ctx.getChild(i).getText());
			}
			this.packageName = builder.toString();
		}
		System.out.println(this.packageName);
	}
	
	
	private List<String> imports = new ArrayList<String>();
	
	@Override
	public void enterSingleTypeImportDeclaration(SingleTypeImportDeclarationContext ctx) {
		super.enterSingleTypeImportDeclaration(ctx);
		StringBuilder builder = new StringBuilder();
		builder.append(ctx.getChild(0).getText()).append(" ");
		for(int i = 1;i < ctx.getChildCount();i ++){
			builder.append(ctx.getChild(i).getText());
		}
		System.out.println(builder.toString());
		this.imports.add(builder.toString());
	}
	
	
	@Override
	public void enterConstructorDeclaration(ConstructorDeclarationContext ctx){
		super.enterConstructorDeclaration(ctx);
	}
	
	@Override
	public void enterFieldDeclaration(FieldDeclarationContext ctx){
		super.enterFieldDeclaration(ctx);
		StringBuilder builder = new StringBuilder();
		for(FieldModifierContext fmCtx: ctx.fieldModifier()){
			builder.append(fmCtx.getText()).append(" ");
		}
		builder.append(ctx.unannType().getText()).append(" ");
		
		builder.append(ctx.variableDeclaratorList().getText());
		System.out.println(builder.toString());
	}
	
	
	public void enterMethodDeclaration(MethodDeclarationContext ctx){
		super.enterMethodDeclaration(ctx);
		StringBuilder builder = new StringBuilder();
		List<MethodModifierContext> mmCtxs = ctx.methodModifier();
		for(MethodModifierContext mmCtx : mmCtxs){
			builder.append(mmCtx.getText()).append(" ");
		}
		MethodHeaderContext mhCtx = ctx.methodHeader();
		
		mhCtx.result();
		mhCtx.typeParameters();
		mhCtx.annotation();
		mhCtx.throws_();

		
		MethodDeclaratorContext mdCtx = mhCtx.methodDeclarator();
		builder.append(mdCtx.Identifier()).append("(");
		
		FormalParameterListContext fplCtx = mdCtx.formalParameterList();
		if(fplCtx != null){
			ReceiverParameterContext rpCtx = fplCtx.receiverParameter();
			if(rpCtx != null){
				builder.append(rpCtx.unannType().getText()).append(" ");
				builder.append(rpCtx.Identifier()).append(" ");
			}
			FormalParametersContext fpsCtx = fplCtx.formalParameters();
			if(fpsCtx != null){
				List<FormalParameterContext> fpCtxs = fpsCtx.formalParameter();
				for(FormalParameterContext fpCtx: fpCtxs){
					builder.append(fpCtx.unannType().getText()).append(" ");
					builder.append(fpCtx.variableDeclaratorId()).append(" ");
				}
			}
			LastFormalParameterContext lfpCtx = fplCtx.lastFormalParameter();
			if(lfpCtx != null){
				if(lfpCtx.unannType() != null){
					builder.append(lfpCtx.unannType().getText()).append(" ");
					builder.append(lfpCtx.variableDeclaratorId()).append(" ");
				} else {
					FormalParameterContext fpCtx = lfpCtx.formalParameter();
					builder.append(fpCtx.unannType().getText()).append(" ");
					builder.append(fpCtx.variableDeclaratorId().getText()).append(" ");
				}
			}
		}
		builder.append(")");
		System.out.println(builder.toString());
	}
	
	
}
