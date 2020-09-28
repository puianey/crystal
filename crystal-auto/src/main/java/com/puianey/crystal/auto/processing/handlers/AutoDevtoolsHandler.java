package com.puianey.crystal.auto.processing.handlers;

import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.util.Set;

//@Tag({
//	Constants.AutoType.AUTO_CONFIGURATION,
//	Constants.AutoType.AUTO_CONTEXT_INITIALIZER
//})
//@AutoService(AnnotationHandler.class)
public class AutoDevtoolsHandler extends AnnotationHandler {

	@Override
	public void preHandle(Set<? extends Element> elements, TypeElement typeElement) {
//		log("devtools--" + typeElement.getQualifiedName().toString());
//		elements.forEach(element -> log("devtools--" + element.toString()));
	}

	@Override
	public void doHandle() {
//		log("devtools--doHandle");
	}
}
