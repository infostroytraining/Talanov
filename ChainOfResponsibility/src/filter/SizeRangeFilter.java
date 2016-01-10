package filter;

import java.io.File;

public class SizeRangeFilter extends Filter {

    private long from, to;

    public SizeRangeFilter(Filter nextFilter, long from, long to) {
        super(nextFilter);
        this.from = from;
        this.to = to;
    }

    @Override
    public boolean currentAccept(File file) {
        if (file != null) {
            long size = file.length();
            return size >= from && size <= to;
        }
        return false;
    }
}
