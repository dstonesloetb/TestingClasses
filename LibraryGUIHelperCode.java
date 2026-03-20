
//Code to add to end of Library GUI before main method 

// ---------------- UTILS ----------------
    private Member selectMember() {
        String[] memberArray = members.values().stream()
                .map(m -> m.getMemberId() + " - " + m.getName())
                .toArray(String[]::new);
        JComboBox<String> memberBox = new JComboBox<>(memberArray);
        int result = JOptionPane.showConfirmDialog(this, memberBox, "Select Member", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            return new ArrayList<>(members.values()).get(memberBox.getSelectedIndex());
        }
        return null;
    }

    private Book selectBook() {
        String[] bookArray = books.stream()
                .map(b -> b.getItemId() + " - " + b.getTitle() + (b.isAvailable() ? " (Available)" : " (On Loan)"))
                .toArray(String[]::new);
        JComboBox<String> bookBox = new JComboBox<>(bookArray);
        int result = JOptionPane.showConfirmDialog(this, bookBox, "Select Book", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            return books.get(bookBox.getSelectedIndex());
        }
        return null;
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
