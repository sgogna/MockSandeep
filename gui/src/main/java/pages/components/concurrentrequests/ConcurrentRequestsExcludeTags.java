/* Copyright 2009 EB2 International Limited */
package pages.components.concurrentrequests;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.sabre.ssw.proxy.concurrent.managment.ConcurrentXMLTag;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.Item;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.BaseTheme;

public class ConcurrentRequestsExcludeTags extends CustomComponent
{

    @AutoGenerated
    private VerticalLayout mainLayout;
    @AutoGenerated
    private HorizontalLayout horizontalLayout_1;
    @AutoGenerated
    private Button addNewExcludeTag;
    @AutoGenerated
    private Table excludeTagTable;
    @AutoGenerated
    private Label label_1;

    /*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

    /*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

    private static final long serialVersionUID = 1L;

    /*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

    private static final String DELETE = "DELETE";
    private static final String ATTRIBUTE = "ATTRIBUTE";
    private static final String TAG = "TAG";
    private ConcurrentRequestChangeFlag changeFlag;
    private ConcurrentRequestsExcludeTagEditWindow editWindow = new ConcurrentRequestsExcludeTagEditWindow();
    private File selectedFile;

    public ConcurrentRequestsExcludeTags()
    {
        buildMainLayout();
        setCompositionRoot(mainLayout);

        initialize();
    }

    private void initialize()
    {
        excludeTagTable.addContainerProperty(TAG, String.class, null);
        excludeTagTable.addContainerProperty(ATTRIBUTE, String.class, null);
        excludeTagTable.addContainerProperty(DELETE, Button.class, null);
        excludeTagTable.setImmediate(true);
        excludeTagTable.setSelectable(true);
        excludeTagTable.addListener(new ItemClickListener()
        {

            private static final long serialVersionUID = 1L;

            @Override
            public void itemClick(ItemClickEvent event)
            {
                if (event.isDoubleClick())
                {
                    editWindow.show(selectedFile, (ConcurrentXMLTag) event.getItemId());
                    getWindow().addWindow(editWindow);
                }
            }
        });

        editWindow.addButtonListener(new ClickListener()
        {

            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(ClickEvent event)
            {
                setExcludeTag(editWindow.getExcludeTag(), editWindow.isNew());
                getWindow().removeWindow(editWindow);
            }
        });

        addNewExcludeTag.addListener(new ClickListener()
        {

            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(ClickEvent event)
            {
                editWindow.show(selectedFile, null);
                getWindow().addWindow(editWindow);
            }
        });
    }

    public void setExcludeList(List<ConcurrentXMLTag> xmlExcludes)
    {
        excludeTagTable.removeAllItems();
        for (final ConcurrentXMLTag concurrentXMLExclude : xmlExcludes)
        {
            addNewExcludeToTable(concurrentXMLExclude);
        }
    }

    private void addNewExcludeToTable(final ConcurrentXMLTag concurrentXMLExclude)
    {
        Button b = createNewDeleteButton(concurrentXMLExclude);
        Object[] objects = new Object[] { concurrentXMLExclude.getTag(), concurrentXMLExclude.getAttributes(), b };
        excludeTagTable.addItem(objects, concurrentXMLExclude);
    }

    private Button createNewDeleteButton(final ConcurrentXMLTag concurrentXMLExclude)
    {
        Button b = new Button("Delete");
        b.setStyleName(BaseTheme.BUTTON_LINK);
        b.setIcon(new ThemeResource("icons/delete.gif"));
        b.addListener(new ClickListener()
        {

            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(ClickEvent event)
            {
                excludeTagTable.removeItem(concurrentXMLExclude);
                changeFlag.setChange(true);
            }
        });
        return b;
    }

    private void setExcludeTag(ConcurrentXMLTag tag, boolean isNew)
    {
        if (isNew)
        {
            addNewExcludeToTable(tag);
        }
        else
        {
            updateExcludeById(tag);
        }
        changeFlag.setChange(true);
    }

    private void updateExcludeById(ConcurrentXMLTag tag)
    {
        Item item = excludeTagTable.getItem(tag);
        item.getItemProperty(TAG).setValue(tag.getTag());
        item.getItemProperty(ATTRIBUTE).setValue(tag.getAttributes());
    }

    public List<ConcurrentXMLTag> getExcludeList()
    {
        List<ConcurrentXMLTag> excludeList = new ArrayList<ConcurrentXMLTag>();
        for (Iterator< ? > iterator = excludeTagTable.getItemIds().iterator(); iterator.hasNext();)
        {
            Object o = iterator.next();
            excludeList.add(new ConcurrentXMLTag(excludeTagTable.getItem(o).getItemProperty(TAG).getValue().toString(), excludeTagTable.getItem(o).getItemProperty(ATTRIBUTE).getValue().toString()));

        }
        return excludeList;
    }

    public void setChangeFlag(ConcurrentRequestChangeFlag changeFlag)
    {
        this.changeFlag = changeFlag;
    }

    @AutoGenerated
    private VerticalLayout buildMainLayout()
    {
        // common part: create layout
        mainLayout = new VerticalLayout();
        mainLayout.setImmediate(false);
        mainLayout.setWidth("100%");
        mainLayout.setHeight("100%");
        mainLayout.setMargin(true);

        // top-level component properties
        setWidth("100.0%");
        setHeight("100.0%");

        // label_1
        label_1 = new Label();
        label_1.setStyleName("border-label");
        label_1.setImmediate(false);
        label_1.setWidth("-1px");
        label_1.setHeight("-1px");
        label_1.setValue("Skipped tags and attributes");
        mainLayout.addComponent(label_1);

        // excludeTagTable
        excludeTagTable = new Table();
        excludeTagTable.setImmediate(false);
        excludeTagTable.setWidth("100.0%");
        excludeTagTable.setHeight("100.0%");
        mainLayout.addComponent(excludeTagTable);
        mainLayout.setExpandRatio(excludeTagTable, 1.0f);

        // horizontalLayout_1
        horizontalLayout_1 = buildHorizontalLayout_1();
        mainLayout.addComponent(horizontalLayout_1);

        return mainLayout;
    }

    @AutoGenerated
    private HorizontalLayout buildHorizontalLayout_1()
    {
        // common part: create layout
        horizontalLayout_1 = new HorizontalLayout();
        horizontalLayout_1.setImmediate(false);
        horizontalLayout_1.setWidth("100.0%");
        horizontalLayout_1.setHeight("-1px");
        horizontalLayout_1.setMargin(false);

        // addNewExcludeTag
        addNewExcludeTag = new Button();
        addNewExcludeTag.setCaption("Add new tag");
        addNewExcludeTag.setImmediate(true);
        addNewExcludeTag.setWidth("-1px");
        addNewExcludeTag.setHeight("-1px");
        horizontalLayout_1.addComponent(addNewExcludeTag);

        return horizontalLayout_1;
    }

    public void setHashFile(Object value)
    {
        if (value == null) {
            this.selectedFile = null;
            return;
        }
            this.selectedFile = (File) value;
    }
}
