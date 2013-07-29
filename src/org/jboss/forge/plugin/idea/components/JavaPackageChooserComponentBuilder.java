package org.jboss.forge.plugin.idea.components;

import com.intellij.analysis.AnalysisScope;
import com.intellij.ide.DataManager;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.actionSystem.DataKeys;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.packageDependencies.DependencyValidationManager;
import com.intellij.packageDependencies.DependencyValidationManagerImpl;
import com.intellij.packageDependencies.ForwardDependenciesBuilder;
import org.jboss.forge.addon.convert.Converter;
import org.jboss.forge.addon.convert.ConverterFactory;
import org.jboss.forge.addon.ui.context.UIContext;
import org.jboss.forge.addon.ui.hints.InputType;
import org.jboss.forge.addon.ui.input.InputComponent;
import org.jboss.forge.addon.ui.input.UICompleter;
import org.jboss.forge.addon.ui.input.UIInput;
import org.jboss.forge.addon.ui.util.InputComponents;
import org.jboss.forge.plugin.idea.ForgeService;
import org.jboss.forge.plugin.idea.wizards.ForgeWizardStep;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by andrei on 29/07/13
 */
public class JavaPackageChooserComponentBuilder extends ComponentBuilder {

	@Override
	public JComponent build(final InputComponent<?, Object> input,
	                        Container container)
	{
		// TODO extend TextBox functionality
		final JTextField textField = new JTextField();
		// Set Default Value
		final ConverterFactory converterFactory = ForgeService.INSTANCE
				.lookup(ConverterFactory.class);
		Converter<Object, String> converter = converterFactory.getConverter(
				input.getValueType(), String.class);
		String value = converter.convert(InputComponents.getValueFor(input));
		textField.setText(value == null ? "" : value);

		textField.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void removeUpdate(DocumentEvent e)
			{
				InputComponents.setValueFor(converterFactory, input,
						textField.getText());
			}

			@Override
			public void insertUpdate(DocumentEvent e)
			{
				InputComponents.setValueFor(converterFactory, input,
						textField.getText());
			}

			@Override
			public void changedUpdate(DocumentEvent e)
			{
				InputComponents.setValueFor(converterFactory, input,
						textField.getText());
			}
		});
		String labelValue = input.getLabel() == null ? input.getName() : input
				.getLabel();
		container.add(new JLabel(labelValue));
		container.add(textField);
		return textField;
	}

	/**
	 * Adds the project source packages if no completer was specified for the
	 * input
	 */
	protected void decorateContainerText(ForgeWizardStep page,
	                                     InputComponent<?, Object> input
	                                     /*, Text container á la Eclipse Text */)
	{

		if (InputComponents.getCompleterFor(input) != null)
		{
			return;
		}

		// get current IntelliJ project or module

		UICompleter<Object> completer = new UICompleter<Object>() {

			@Override
			public Iterable<String> getCompletionProposals(UIContext uiContext,
			                                               InputComponent<?, Object> objectInputComponent,
			                                               String s)
			{
				Set<String> proposals = new TreeSet<String>();

				// TODO retrieve list of available packages
//				DataContext dataContext = DataManager.getInstance().getDataContext();
//				Project project = DataKeys.PROJECT.getData(dataContext);
//
//				assert project != null;
//				DependencyValidationManager dvm = new DependencyValidationManagerImpl(project);

				return proposals;
			}
		};
	}

	@Override
	protected Class<String> getProducedType()
	{
		return String.class;
	}

	@Override
	protected InputType getSupportedInputType()
	{
		return InputType.JAVA_PACKAGE_PICKER;
	}

	@Override
	protected Class<?>[] getSupportedInputComponentTypes()
	{
		return new Class<?>[]{UIInput.class};
	}
}
