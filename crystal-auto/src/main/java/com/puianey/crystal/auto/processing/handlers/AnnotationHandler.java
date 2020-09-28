package com.puianey.crystal.auto.processing.handlers;

import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;
import java.util.Set;

public abstract class AnnotationHandler {

	Filer filer;

	Messager messager;

	Elements elementUtil;

	public abstract void preHandle(Set<? extends Element> elements, TypeElement typeElement);

	public abstract void doHandle();

	public void setProcessingEnv(ProcessingEnvironment processingEnv) {
		this.filer = processingEnv.getFiler();
		this.messager = processingEnv.getMessager();
		this.elementUtil = processingEnv.getElementUtils();
	}

	protected void log(String msg) {
		messager.printMessage(Diagnostic.Kind.NOTE, msg);
	}

}
