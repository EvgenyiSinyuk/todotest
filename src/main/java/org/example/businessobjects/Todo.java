package org.example.businessobjects;

import lombok.Getter;

@Getter
public class Todo {

    private String text;
    private boolean completed;
    private Long id;

    public Todo(String text, Boolean completed, String id) {
        this.text = text;
        this.completed = completed;
        this.id = Long.parseUnsignedLong(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Todo) {
            return ((Todo) obj).completed == this.completed &&
                    ((Todo) obj).id.equals(this.id) &&
                    ((Todo) obj).text.equals(this.text);
        }
        return false;
    }
}
