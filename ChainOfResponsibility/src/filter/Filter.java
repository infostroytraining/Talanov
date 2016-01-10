package filter;


import java.io.File;

public abstract class Filter {

    private Filter nextFilter;

    protected Filter(Filter nextFilter){
        this.nextFilter = nextFilter;
    }

    public boolean accept(File file){
        boolean result = currentAccept(file);
        if(nextFilter != null && result){
            return nextFilter.accept(file);
        }
        return result;
    }

    public abstract boolean currentAccept(File file);

}
