package tech.dsckiet.ideasharing;

public class IdeasRecyclerView {
    private String ideaTitle,ideaDesc;

    public IdeasRecyclerView(){

    }

    public IdeasRecyclerView(String ideaTitle, String ideaDesc){
        this.ideaTitle = ideaTitle;
        this.ideaDesc = ideaDesc;
    }

    public String getIdeaTitle() {
        return ideaTitle;
    }

    public void setIdeaTitle(String ideaTitle) {
        this.ideaTitle = ideaTitle;
    }

    public String getIdeaDesc() {
        return ideaDesc;
    }

    public void setIdeaDesc(String ideaDesc) {
        this.ideaDesc = ideaDesc;
    }
}
