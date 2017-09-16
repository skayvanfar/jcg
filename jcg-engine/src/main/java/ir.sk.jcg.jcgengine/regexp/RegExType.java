package ir.sk.jcg.jcgengine.regexp;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 9/16/2017.
 */
public enum RegExType {
    JAVA_REG_EX_TYPE(0, "(?s)/\\* GENERATED CODE SECTION TYPE\\((?<type>%s)\\)(((?!GENERATED CODE SECTION END).)*?)/\\* GENERATED CODE END \\*/",
            "(?s)/\\* GENERATED CODE SECTION TYPE\\((?<type>%s)\\)(((?!GENERATED CODE SECTION END).*%s.*)*?)/\\* GENERATED CODE END \\*/",
            "(?s)/\\* GENERATED CODE SECTION TYPE\\((?<type>%s)\\)(((?!GENERATED CODE SECTION END).)*?)(?<insertPlace>)/\\* GENERATED CODE END \\*/",
            "/\\*", "\\*/"),
    JSP_REG_EX_TYPE(1, "(?s)/\\* GENERATED CODE SECTION TYPE\\((?<type>%s)\\)(((?!GENERATED CODE SECTION END).)*?)/\\* GENERATED CODE END \\*/",
            "(?s)/\\* GENERATED CODE SECTION TYPE\\((?<type>%s)\\)(((?!GENERATED CODE SECTION END).*%s.*)*?)/\\* GENERATED CODE END \\*/",
            "(?s)/\\* GENERATED CODE SECTION TYPE\\((?<type>%s)\\)(((?!GENERATED CODE SECTION END).)*?)(?<insertPlace>)/\\* GENERATED CODE END \\*/",
            "", ""),
    XML_REG_EX_TYPE(2, "(?s)<!-- GENERATED CODE SECTION TYPE\\((?<type>%s)\\)(((?!GENERATED CODE SECTION END).)*?)(?<insertPlace>)<!-- GENERATED CODE END -->",
            "(?s)<!-- GENERATED CODE SECTION TYPE\\((?<type>%s)\\)(((?!GENERATED CODE SECTION END).*%s.*)*?)<!-- GENERATED CODE END -->",
            "(?s)<!-- GENERATED CODE SECTION TYPE\\((?<type>%s)\\)(((?!GENERATED CODE SECTION END).)*?)(?<insertPlace>)<!-- GENERATED CODE END -->",
            "<!--", "-->");

    private Integer value;
    private String findSectionExpression;
    private String hasModelElementExpression;
    private String addModelElementExpression;
    private String startCharacters;
    private String endCharacters;

    RegExType(Integer value, String findSectionExpression, String hasModelElementExpression, String addModelElementExpression, String startCharacters, String endCharacters) {
        this.value = value;
        this.findSectionExpression = findSectionExpression;
        this.hasModelElementExpression = hasModelElementExpression;
        this.addModelElementExpression = addModelElementExpression;
        this.startCharacters = startCharacters;
        this.endCharacters = endCharacters;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getFindSectionExpression() {
        return findSectionExpression;
    }

    public void setFindSectionExpression(String findSectionExpression) {
        this.findSectionExpression = findSectionExpression;
    }

    public String getHasModelElementExpression() {
        return hasModelElementExpression;
    }

    public void setHasModelElementExpression(String hasModelElementExpression) {
        this.hasModelElementExpression = hasModelElementExpression;
    }

    public String getAddModelElementExpression() {
        return addModelElementExpression;
    }

    public void setAddModelElementExpression(String addModelElementExpression) {
        this.addModelElementExpression = addModelElementExpression;
    }

    public String getStartCharacters() {
        return startCharacters;
    }

    public void setStartCharacters(String startCharacters) {
        this.startCharacters = startCharacters;
    }

    public String getEndCharacters() {
        return endCharacters;
    }

    public void setEndCharacters(String endCharacters) {
        this.endCharacters = endCharacters;
    }

}
