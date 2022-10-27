package test.graham.entities;

import java.util.Objects;

public class Ticket {

    private int id; //
    private String description; //For strings the default value is "" (an empty string)
    private String createdBy; //For strings the default value is "" (an empty string)
    private String approvedBy;
    private int amount; //defaults to 0
    private Status status; // defualts to 0 - 0=pending, 1=approved, 2= denied

    private Boolean isChanged;


    public Ticket() {
    }

    public Ticket(int id, String description, String createdBy, String approvedBy, int amount, Status status, Boolean isChanged) {
        this.id = id;
        this.description = description;
        this.createdBy = createdBy;
        this.approvedBy = approvedBy;
        this.amount = amount;
        this.status = status;
        this.isChanged = isChanged;
    }

    public Ticket(String createdBy, String approvedBy, Status status, Boolean isChanged) {
        this.createdBy = createdBy;
        this.approvedBy = approvedBy;
        this.status = status;
        this.isChanged = isChanged;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", approvedBy='" + approvedBy + '\'' +
                ", amount=" + amount +
                ", status=" + status +
                ", isChanged=" + isChanged +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return id == ticket.id && amount == ticket.amount && Objects.equals(description, ticket.description) && Objects.equals(createdBy, ticket.createdBy) && Objects.equals(approvedBy, ticket.approvedBy) && status == ticket.status && Objects.equals(isChanged, ticket.isChanged);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, createdBy, approvedBy, amount, status, isChanged);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Boolean getChanged() {
        return isChanged;
    }

    public void setChanged(Boolean changed) {
        isChanged = changed;
    }
}

