package org.jboss.forge.plugin.idea.components;

import org.jboss.forge.addon.ui.hints.InputType;
import org.jboss.forge.addon.ui.input.InputComponent;
import org.jboss.forge.addon.ui.input.UISelectMany;

import javax.swing.*;
import java.awt.*;

/**
 * Created by andrei on 25/07/13
 */
public class CheckboxTableComponentBuilder extends ComponentBuilder {

	@Override
	public JComponent build(InputComponent<?, Object> input, Container container)
	{
		// TODO implement this
		return new JPanel();
	}

	@Override
	protected Class<?> getProducedType()
	{
		return Object.class;
	}

	@Override
	protected InputType getSupportedInputType()
	{
		return InputType.SELECT_MANY_CHECKBOX;
	}

	@Override
	protected Class<?>[] getSupportedInputComponentTypes()
	{
		return new Class<?>[]{UISelectMany.class};
	}
}
