package com.system;

import com.system.dto.DragonInfo;
import com.system.dto.PageInfo;
import com.system.util.GetCodeTypeUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by yangqian on 2020/1/16.
 */
public class Test {
    public static void main(String[] args) {

        List<String> list = Arrays.asList("688019","300191","300708","000669","000536","002442","600078","002201","688019","688002","002201","300127","603882","002881","600939","002903","603648","002201","300484","600448","688122","300023","002169","002639","600707","600722","002386","002068","688122","688009","000687","300748","000590","603327","300466","002943","300620","000687","002141","603335","688016","600759","002185","600860","002426","002068","603979","300398","688016","688010","002513","600980","603233","300565","000557","002045","603499","002513","002922");
        for(String code :list){
            String tsCode = "";
            String codeType = GetCodeTypeUtils.getCodeType(code);
            if (code.toLowerCase().contains(codeType)) {
                tsCode = codeType + code.toLowerCase().replace(codeType, "");
            } else {
                tsCode = codeType + code;
            }
            System.out.println(tsCode);
        }


    }
}
