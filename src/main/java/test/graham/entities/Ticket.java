package test.graham.entities;

public class Ticket {

    private int id; //
    private String description; //For strings the default value is "" (an empty string)
    private String createdBy; //For strings the default value is "" (an empty string)
    private String approvedBy;
    private int amount; //defaults to 0
    private int status; // defualts to 0 - 0=pending, 1=approved, 2= denied

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", approvedBy='" + approvedBy + '\'' +
                ", amount=" + amount +
                ", status=" + status +
                '}';
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
