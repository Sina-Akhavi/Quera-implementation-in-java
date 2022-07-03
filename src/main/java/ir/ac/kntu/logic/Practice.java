package ir.ac.kntu.logic;

import java.util.ArrayList;

public class Practice {
    private ArrayList<User> courseUsers;

    private ArrayList<Question> questions;

    private RankTable rankTable;

    private String name;

    private String description;

    private Date start;

    private Date deadline;

    private double delayCoefficient;

    private boolean isVisible;

    public Practice(String name, String description, Date start, Date deadline,
            double delayCoefficient) {
        this.name = name;
        this.description = description;
        this.start = start;
        this.deadline = deadline;
        this.delayCoefficient = delayCoefficient;
        this.courseUsers = new ArrayList<>();
        this.questions = new ArrayList<>();
        this.rankTable = new RankTable(questions);
        this.isVisible = true;
    }

    public Practice(String name, String description, Date deadline, double delayCoefficient) {
        this.name = name;
        this.description = description;
        this.deadline = deadline;
        this.delayCoefficient = delayCoefficient;
    }

    public boolean addQuestion(Question question) {
        question.setDeadline(deadline);
        if (questions.contains(question)) {
            return false;
        }
        questions.add(question);
        return true;
    }

    public ArrayList<User> getCourseUsers() {
        return courseUsers;
    }

    public void setCourseUsers(ArrayList<User> users) {
        courseUsers = users;
        rankTable.setUsers(users);
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public RankTable getRankTable() {
        return rankTable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public double getDelayCoefficient() {
        return delayCoefficient;
    }

    public void setDelayCoefficient(double delayCoefficient) {
        this.delayCoefficient = delayCoefficient;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((deadline == null) ? 0 : deadline.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((start == null) ? 0 : start.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Practice other = (Practice) obj;
        if (deadline == null) {
            if (other.deadline != null) {
                return false;
            }
        } else if (!deadline.equals(other.deadline)) {
            return false;
        }
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        if (start == null) {
            if (other.start != null) {
                return false;
            }
        } else if (!start.equals(other.start)) {
            return false;
        }
        return true;
    }
}
