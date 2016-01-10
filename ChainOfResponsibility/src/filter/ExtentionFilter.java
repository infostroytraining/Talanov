package filter;


import com.google.common.io.Files;

import java.io.File;

public class ExtentionFilter extends Filter {

    private String extention;

    public ExtentionFilter(Filter nextFilter, String extention) {
        super(nextFilter);
        this.extention = extention;
    }

    @Override
    public boolean currentAccept(File file) {
        if(file != null){
            String fileExtention = Files.getFileExtension(file.getName());
            return fileExtention.equalsIgnoreCase(extention);
        }
        return false;
    }
}
