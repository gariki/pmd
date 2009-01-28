package net.sourceforge.pmd.eclipse.ui.preferences.editors;

import net.sourceforge.pmd.PropertyDescriptor;
import net.sourceforge.pmd.Rule;
import net.sourceforge.pmd.eclipse.ui.preferences.br.SizeChangeListener;
import net.sourceforge.pmd.eclipse.ui.preferences.br.ValueChangeListener;
import net.sourceforge.pmd.lang.rule.properties.PropertyDescriptorWrapper;
import net.sourceforge.pmd.lang.rule.properties.TypeProperty;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;


/**
 * 
 * @author Brian Remedios
 */
public class TypeEditorFactory2 extends AbstractEditorFactory {

	public static final TypeEditorFactory2 instance = new TypeEditorFactory2();

	private TypeEditorFactory2() { }
	   
	protected void fillWidget(TypeText textWidget, PropertyDescriptor<?> desc, Rule rule) {
		
		Class<?> type = (Class<?>)rule.getProperty(desc);
		textWidget.setType(type);
	}
		
    private static TypeProperty typePropertyFrom(PropertyDescriptor<?> desc) {
        
        if (desc instanceof PropertyDescriptorWrapper) {
           return (TypeProperty) ((PropertyDescriptorWrapper<?>)desc).getPropertyDescriptor();
        } else {
            return (TypeProperty)desc;
        }
    }
	
    /**
     * 
     * @param parent Composite
     * @param columnIndex int
     * @param desc PropertyDescriptor
     * @param rule Rule
     * @param listener ValueChangeListener
     * @return Control
     * @see net.sourceforge.pmd.ui.preferences.br.EditorFactory#newEditorOn(Composite, int, PropertyDescriptor, Rule)
     */
    public Control newEditorOn(Composite parent, int columnIndex, final PropertyDescriptor<?> desc, final Rule rule, final ValueChangeListener listener, SizeChangeListener sizeListener) {
        
        if (columnIndex == 0) return addLabel(parent, desc);
        
        if (columnIndex == 1) {
            
            final TypeText typeText = new TypeText(parent, SWT.SINGLE | SWT.BORDER, true);
            typeText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

            fillWidget(typeText, desc, rule);
                        
            final TypeProperty tp = typePropertyFrom(desc);
            
            typeText.addListener(SWT.FocusOut, new Listener() {
                public void handleEvent(Event event) {
                    Class<?> newValue = typeText.getType(true);
                    if (newValue == null) return;
                    
                    Class<?> existingValue = rule.getProperty(tp);                
                    if (existingValue == newValue) return;              
                    
                    rule.setProperty(tp, newValue);
                    listener.changed(rule, desc, newValue);
                }
            });

            return typeText;
        }
        
        return null;
    }

}