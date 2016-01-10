package filter;


import java.io.File;

public class DateRangeFilter extends Filter {

    private long from, to;

    public DateRangeFilter(Filter nextFilter, long from, long to) {
        super(nextFilter);
        this.from = from;
        this.to = to;
    }

    @Override
    public boolean currentAccept(File file) {
        if(file != null){
            long lastModified = file.lastModified();
            return lastModified >= from && lastModified <= to;
        }
        return false;
    }
}
