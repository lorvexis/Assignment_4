package com.example.demo;
import java.util.Objects;

public class LibraryMember extends Person {

    private int memberId;

    public LibraryMember(int memberId, String name) {
        super(name);
        this.memberId = memberId;
    }

    public int getMemberId() {
        return memberId;
    }

    @Override
    public String getRole() {
        return "Library Member";
    }

    @Override
    public String toString() {
        return "LibraryMember{id=" + memberId + ", name='" + name + "'}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LibraryMember)) return false;
        LibraryMember that = (LibraryMember) o;
        return memberId == that.memberId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberId);
    }
}
