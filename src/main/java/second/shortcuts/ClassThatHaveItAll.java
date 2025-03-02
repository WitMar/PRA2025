package second.shortcuts;

import java.util.List;

public class ClassThatHaveItAll implements InterfaceOne {
    String name;
    Integer number;
    List<Long> list;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public List<Long> getList() {
        return list;
    }

    public void setList(List<Long> list) {
        this.list = list;
    }

    @Override
    public void printMe(String info) {
    }
}
