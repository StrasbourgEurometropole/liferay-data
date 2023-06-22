/**
 * Copyright (c) 2014-2016, CKSource - Frederico Knabben. All rights reserved.
 * Licensed under the terms of the MIT License (see LICENSE.md).
 *
 * Simple CKEditor Widget (Part 1).
 *
 * Created out of the CKEditor Widget SDK:
 * http://docs.ckeditor.com/#!/guide/widget_sdk_tutorial_1
 */

// Register the plugin within the editor.
CKEDITOR.plugins.add("simplebox", {
  // This plugin requires the Widgets System defined in the 'widget' plugin.
  requires: "widget",

  // The plugin initialization logic goes inside this method.
  init: function(editor) {
    var instance = this;

    // Register the editing dialog.
    CKEDITOR.dialog.add( 'simplebox', this.path + 'dialogs/simplebox.js' );

    // Register the simplebox widget.
    editor.widgets.add("simplebox", {
      // Minimum HTML which is required by this widget to work.
      requiredContent: "div(simplebox)",

      // Set the widget dialog window name. This enables the automatic widget-dialog binding.
      // This dialog window will be opened when creating a new widget or editing an existing one.
      dialog: 'simplebox',

      // Define two nested editable areas.
      editables: {
        title: {
          // Define a CSS selector used for finding the element inside the widget element.
          selector: ".simplebox-title",
          // Define content allowed in this nested editable. Its content will be
          // filtered accordingly and the toolbar will be adjusted when this editable
          // is focused.
          allowedContent: "br strong em"  
        },
        content: {
          selector: ".simplebox-content",
          allowedContent: "p br ul ol li strong em"
        }
      },

      // Define the template of a new Simple Box widget.
      // The template will be used when creating new instances of the Simple Box widget.
      template:
        '<div class="simplebox">' +
        '<h2 class="simplebox-title">Title</h2>' +
        '<div class="simplebox-content"><p>Content...</p></div>' +
        "</div>",

      // Check the elements that need to be converted to widgets.
      //
      // Note: The "element" argument is an instance of http://docs.ckeditor.com/#!/api/CKEDITOR.htmlParser.element
      // so it is not a real DOM element yet. This is caused by the fact that upcasting is performed
      // during data processing which is done on DOM represented by JavaScript objects.
      upcast: function(element) {
        // Return "true" (that element needs to converted to a Simple Box widget)
        // for all <div> elements with a "simplebox" class.
        return element.name == "div" && element.hasClass("simplebox");
      },

      // When a widget is being initialized, we need to read the data ("align" and "width")
      // from DOM and set it by using the widget.setData() method.
      // More code which needs to be executed when DOM is available may go here.
      init: function() {
        var width = this.element.getStyle( 'width' );
        if ( width )
          this.setData( 'width', width );

        if ( this.element.hasClass( 'align-left' ) )
          this.setData( 'align', 'left' );
        if ( this.element.hasClass( 'align-right' ) )
          this.setData( 'align', 'right' );
        if ( this.element.hasClass( 'align-center' ) )
          this.setData( 'align', 'center' );
      },

      // Listen on the widget#data event which is fired every time the widget data changes
      // and updates the widget's view.
      // Data may be changed by using the widget.setData() method, which we use in the
      // Simple Box dialog window.
      data: function() {
        // Check whether "width" widget data is set and remove or set "width" CSS style.
        // The style is set on widget main element (div.simplebox).
        if ( this.data.width == '' )
          this.element.removeStyle( 'width' );
        else
          this.element.setStyle( 'width', this.data.width );

        // Brutally remove all align classes and set a new one if "align" widget data is set.
        this.element.removeClass( 'align-left' );
        this.element.removeClass( 'align-right' );
        this.element.removeClass( 'align-center' );
        if ( this.data.align )
          this.element.addClass( 'align-' + this.data.align );
      }
    });

    // Command to add a sample widget
    editor.addCommand('insertSimpleBox', {
      exec: function(editor) {
        editor.insertHtml('<div class="simplebox"><h2 class="simplebox-title">Title</h2><p class="simplebox-content">Content</p></div>')
      }
    });

    // Button for the command
    editor.ui.addButton('SimpleBox', {
      label: 'Insérer une SimpleBox',
      command: 'insertSimpleBox',
      icon: instance.path + 'assets/simplebox.png'
    });
    
    // Custom css
    editor.addContentsCss(instance.path + 'assets/simplebox.css' );
  }
});