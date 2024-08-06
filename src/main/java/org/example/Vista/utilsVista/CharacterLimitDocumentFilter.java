package org.example.Vista.utilsVista;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class CharacterLimitDocumentFilter extends DocumentFilter {
    private int maxCharacters;

    public CharacterLimitDocumentFilter(int maxCharacters) {
        this.maxCharacters = maxCharacters;
    }

    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
        if (string == null) {
            return;
        }

        if ((fb.getDocument().getLength() + string.length()) <= maxCharacters) {
            super.insertString(fb, offset, string, attr);
        }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        if (text == null) {
            return;
        }

        if ((fb.getDocument().getLength() - length + text.length()) <= maxCharacters) {
            super.replace(fb, offset, length, text, attrs);
        }
    }

    @Override
    public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
        super.remove(fb, offset, length);
    }
}
