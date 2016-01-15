package util;

/**
 * 字数统计公共类
 * <p>Title: StatWordCount.java</p>
 * <p>Description:语联网</p>
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company:武汉传神网络技术有限公司</p>
 * @author jay.liu
 * @date Dec 31, 2013 9:59:34 AM
 * @version V1.0
 */
public class StatWordCount{

    private final char[] CHS= {',','；','.','!','?',';','+','。','？','！','，','@','#','$','￥','%'}; //符号数组

    private final char[] CHN= {'\n','\r'}; //转义符数组

    private final char[] SPACE= {' ','　'}; //空格的数组(前半角，后全角)

    /**
     * 根据指定条件来筛选文章的字数 
     * @author jay.liu
     * @param wordContent 文章内容
     * @param compriseInterpunction 是否包含指定字符
     * @param compriseSpace 是否包含空格
     * @return 
     * int 返回文章经过指定筛选后的长度
     * @date Dec 31, 2013 10:03:31 AM
     */
    public int getWordCount(String wordContent,boolean compriseInterpunction,boolean compriseSpace){

        if(wordContent == null){
            return 0;
        }
        else if(wordContent.length() == 0){
            return 0;
        }
        else{
            //既要包含符号又要包含空格
            if(compriseInterpunction && compriseSpace){
                //清除转义符
                String regex= "[" + new String(CHN) + "]";
                wordContent= wordContent.replaceAll(regex," ");
                return this.getWordCount(wordContent);
            }
            //不包含符号包含空格
            else if(!compriseInterpunction && compriseSpace){
                //使用正则表达式去掉指定的符号和转义符
                String regex1= "[" + new String(CHN) + "]";
                String regex2= "[" + new String(CHS) + "]";
                wordContent= wordContent.replaceAll(regex1," ");
                wordContent= wordContent.replaceAll(regex2," ");
                return this.getWordCount(wordContent);
            }
            //包含指定符号不包含空格
            else if(compriseInterpunction && !compriseSpace){
                //使用正则表达式去掉空格和转义符
                String regex1= "[" + new String(CHN) + "]";
                String regex2= "[" + new String(SPACE) + "]";
                wordContent= wordContent.replaceAll(regex1," ");
                wordContent= wordContent.replaceAll(regex2," ");
                return this.getWordCount(wordContent);
            }
            //空格和指定符号都不包含
            else{
                //使用正则表达式去掉空格,指定符号和转义符
                String regex1= "[" + new String(CHN) + "]";
                String regex3= "[" + new String(CHS) + "]";
                String regex2= "[" + new String(SPACE) + "]";
                wordContent= wordContent.replaceAll(regex1," ");
                wordContent= wordContent.replaceAll(regex2," ");
                wordContent= wordContent.replaceAll(regex3," ");
                return this.getWordCount(wordContent);
            }
        }

    }

    /**
     * 返回文章中的字数 
     * @author jay.liu
     * @param wordContent 文章内容
     * @return 
     * int 
     * @date Dec 31, 2013 10:05:54 AM
     */
    private int getWordCount(String wordContent){
        int count= 0;
        if(wordContent == null){ //判断是否为null,如果为null直接返回0
            count= 0;
        }
        else if(wordContent.length() == 0){ //判断是否为空,如果为空直接返回0
            count= 0;
        }
        else{ //判断获取字数
            wordContent= wordContent.trim(); //清空空格
            //临时变量
            String s4= "";
            String s3= "";
            String s1= "";
            boolean bb= false;
            if(wordContent.length() > 0){
                s4= String
                        .valueOf(wordContent.charAt(wordContent.length() - 1));
            }
            for(int i= 0;i < wordContent.length();i++){
                s3= String.valueOf(wordContent.charAt(i));
                int num= s3.getBytes().length;
                if(s3.hashCode() == 32 || s3.getBytes().length == 2){
                    bb= true;
                }
                if(num == 2){
                    count++;
                }
                else{
                    if(i + 1 < wordContent.length() && (i > 1)){
                        s1= String.valueOf(wordContent.charAt(i + 1));
                        if((s1.hashCode() == 32 || s1.getBytes().length == 2)
                                && (s3.hashCode() != 32)){
                            count++;
                        }
                    }
                }
            }
            if(!bb){
                count++;
            }
            else{
                if(s4.getBytes().length == 1){
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * 根据条件来获取文章的字符数 
     * @author jay.liu
     * @param wordContent 文章内容
     * @param compriseInterpunction 是否包含指定符号
     * @param compriseSpace 是否包含空格
     * @return 返回字符长度
     * int 
     * @date Dec 31, 2013 10:07:49 AM
     */
    public int getWordCharacter(String wordContent,boolean compriseInterpunction,boolean compriseSpace){

        //既要包含符号又要包含空格
        if(compriseInterpunction && compriseSpace){
            //清除转义符
            String regex= "[" + new String(CHN) + "]";
            wordContent= wordContent.replaceAll(regex," ");
            //首部的空格不算
            wordContent= wordContent.replaceAll("^\\s+","");
            return wordContent.length();
        }//不包含符号包含空格
        else if(!compriseInterpunction && compriseSpace){
            //首部的空格不算
            wordContent= wordContent.replaceAll("^\\s+","");
            //使用正则表达式去掉指定的符号和转义符
            String regex1= "[" + new String(CHN) + "]";
            String regex2= "[" + new String(CHS) + "]";
            wordContent= wordContent.replaceAll(regex1," ");
            wordContent= wordContent.replaceAll(regex2," ");
            return wordContent.length();
        }//包含指定符号不包含空格
        else if(compriseInterpunction && !compriseSpace){
            //使用正则表达式去掉空格和转义符
            return this.getNoSpaceCount(wordContent);
        }//空格和指定符号都不包含
        else{
            //使用正则表达式去掉指定符号
            String regex1= "[" + new String(CHS) + "]";
            wordContent= wordContent.replaceAll(regex1," ");
            return this.getNoSpaceCount(wordContent);
        }

    }
    
    /**
     * 获取文章中非空格的字符总数 
     * @author jay.liu
     * @param wordContent 文章内容
     * @return 
     * int 
     * @date Dec 31, 2013 10:10:44 AM
     */
    private int getNoSpaceCount(String wordContent) {
        
        int spaceCount = 0;
        if(wordContent==null)
        {
            spaceCount = 0;
        }else if(wordContent.length()==0)
        {
            spaceCount = 0;
        }else
        {
            //替换首部的
            wordContent = wordContent.replaceAll("^\\s+","");
            wordContent = wordContent.replaceAll(" ","");
            //使用正则替换转义符
            String regex = "["+new String(CHN)+"]";
            wordContent = wordContent.replaceAll(regex,"");
            spaceCount = wordContent.length();
        }
        return spaceCount;
    }

    public static void main(String[] args){
        StatWordCount statWordCount = new StatWordCount();
        System.out.println(statWordCount.getWordCount("我是中国人，你们是吗?",false,false));
    }
}
