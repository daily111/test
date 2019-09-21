package com.example.test.tool;

public class T1 {
//
//    /**
//     * 用来分页的方法
//     * @param list
//     * @param pagesize
//     * @param pageNo
//     * @return
//     */
//    public Page<MisInfoEntity> fenye(List<MisInfoEntity> list, int pagesize, int pageNo){
//        List<MisInfoEntity> result = new ArrayList<>();
//        Page<MisInfoEntity> page = new Page<>();
//        int totalcount=list.size();
//        int pagecount=0;
//        int m=totalcount%pagesize;
//        if(m>0){
//            pagecount=totalcount/pagesize+1;
//        }else{
//            pagecount=totalcount/pagesize;
//        }
//        if(pagesize>totalcount || pagesize==totalcount){
//            result = list;
//        }else{
//            if(pageNo==pagecount){
//                result= list.subList((pageNo-1)*pagesize,totalcount);
//            }else{
//                result=list.subList((pageNo-1)*pagesize,pagesize*(pageNo));
//            }
//        }
//        page.setRows(result);
//        page.setTotalPages(pagecount);
//        page.setTotal(totalcount);
//
//
//        System.out.println("------------------------------切割线----------------------------------------");
//
//
//
//
//
//
//
//
//
////    for(int i=1;i<=pagecount;i++){
////       if (m==0){
////          List<Integer> subList= list.subList((i-1)*pagesize,pagesize*(i));
////          System.out.println(subList);
////       }else{
////          if (i==pagecount){
////             List<Integer> subList= list.subList((i-1)*pagesize,totalcount);
////             System.out.println(subList);
////          }else{
////             List<Integer> subList= list.subList((i-1)*pagesize,pagesize*(i));
////             System.out.println(subList);
////          }
////       }
////    }
//        return  page;
//    }
}
