import java.util.List;

public class Result implements Comparable<Result>{
    private String carNumber;
    private List<String> ownerNum;
    private List<String> insNum;

    public Result() {
    }

    public Result(String carNumber, List<String> ownerNum, List<String> regNum) {
        this.carNumber = carNumber;
        this.ownerNum = ownerNum;
        this.insNum = regNum;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public List<String> getOwnerNum() {
        return ownerNum;
    }

    public void setOwnerNum(List<String> ownerNum) {
        this.ownerNum = ownerNum;
    }

    public List<String> getInsNum() {
        return insNum;
    }

    public void setInsNum(List<String> insNum) {
        this.insNum = insNum;
    }

    @Override
    public int compareTo(Result o) {
        if(this.insNum.size()>o.insNum.size()) {
            return -1;
        }
        else if(this.insNum.size()<o.insNum.size()){
            return 1;
        }
        return 0;

    }
}
