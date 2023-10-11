package Model;

import java.util.Date;
/**
 * class AuditLog - объект аудита действий игрока
 */
public class AuditLog {
    private String userId;
    private ActionType actionType;
    private Date actionDate;

    public AuditLog(String userId, ActionType actionType) {
        this.userId = userId;
        this.actionType = actionType;
        this.actionDate = new Date();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public void setActionType(ActionType actionType) {
        this.actionType = actionType;
    }

    public Date getActionDate() {
        return actionDate;
    }

    public void setActionDate(Date actionDate) {
        this.actionDate = actionDate;
    }

    public enum ActionType {
        LOGIN,
        LOGOUT,
        DEPOSIT,
        WITHDRAWAL
    }
}
