package com.example.demo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Library {

    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books ORDER BY title ASC";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                books.add(new Book(
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("isbn"),
                        rs.getBoolean("available")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return books;
    }

    public void addBookToDatabase(Book book) {
        String sql = "INSERT INTO books (isbn, title, author, available) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, book.getIsbn());
            stmt.setString(2, book.getTitle());
            stmt.setString(3, book.getAuthor());
            stmt.setBoolean(4, book.isAvailable());

            stmt.executeUpdate();
            System.out.println("Book added to database.");

        } catch (SQLException e) {
            System.out.println("Error adding book.");
            e.printStackTrace();
        }
    }

    // READ
    public void readBooksFromDatabase(int sortChoice) {

        String sql = "SELECT * FROM books";

        switch (sortChoice) {
            case 1:
                sql += " ORDER BY title ASC";
                break;
            case 2:
                sql += " ORDER BY author ASC";
                break;
            case 3:
                sql += " ORDER BY isbn ASC";
                break;
            case 4:
                sql += " ORDER BY available DESC";
                break;
            default:
                // no sort
                break;
        }

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\nBooks in database:");

            while (rs.next()) {
                System.out.println(
                        rs.getString("isbn") + " | " +
                                rs.getString("title") + " | " +
                                rs.getString("author") + " | Available: " +
                                rs.getBoolean("available")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE
    public void deleteBook(String isbn) {
        String sql = "DELETE FROM books WHERE isbn = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, isbn);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void AddMemberToDatabase(LibraryMember member) {
        String sql = "INSERT INTO members(member_id, name) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, member.getMemberId());
            stmt.setString(2, member.getName());

            stmt.executeUpdate();
            System.out.println("Member added");

        } catch(SQLException e) {
            e.printStackTrace();
        }

    }
    public void updateAvailability(String isbn, boolean available) {
        String sql = "UPDATE books SET available = ? WHERE isbn = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setBoolean(1, available);
            stmt.setString(2, isbn);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<LibraryMember> getAllMembers() {
        List<LibraryMember> members = new ArrayList<>();
        String sql = "SELECT member_id, name FROM members ORDER BY member_id";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                members.add(new LibraryMember(
                        rs.getInt("member_id"),
                        rs.getString("name")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return members;
    }

    public void deleteMember(int memberId) {
        String sql = "DELETE FROM members WHERE member_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, memberId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
