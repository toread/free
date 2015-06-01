package com.toread.common.page;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class EasyUiPage implements Pageable {

    private static final long serialVersionUID = 1232825578694716871L;

    private final int page;
    private final int rows;
    private final Sort sort;


    public EasyUiPage(int page, int size){
        this(page, size, (Sort)null);
    }

    public EasyUiPage(int page, int size, Sort.Direction direction, String... properties) {
        this(page,size, new Sort(direction, properties));
    }

    public EasyUiPage(int page, int size, Sort sort) {
        this.page=page;
        this.rows=size;
        this.sort = sort;
    }

    public int getPageSize() {
        return rows;
    }

    /* 
     * (non-Javadoc)
     * @see org.springframework.data.domain.Pageable#getPageNumber()
     */
    public int getPageNumber() {
        return page;
    }

    /* 
     * (non-Javadoc)
     * @see org.springframework.data.domain.Pageable#getOffset()
     */
    public int getOffset() {
        return page * rows;
    }

    /* 
     * (non-Javadoc)
     * @see org.springframework.data.domain.Pageable#hasPrevious()
     */
    public boolean hasPrevious() {
        return page > 0;
    }

    /* 
     * (non-Javadoc)
     * @see org.springframework.data.domain.Pageable#previousOrFirst()
     */
    public Pageable previousOrFirst() {
        return hasPrevious() ? previous() : first();
    }


    public Sort getSort() {
        return this.sort;
    }

    public Pageable next() {
        return new EasyUiPage(this.getPageNumber() + 1, this.getPageSize(), this.getSort());
    }

    public EasyUiPage previous() {
        return this.getPageNumber() == 0?this:new EasyUiPage(this.getPageNumber() - 1, this.getPageSize(), this.getSort());
    }

    public Pageable first() {
        return new EasyUiPage(0, this.getPageSize(), this.getSort());
    }

    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        } else if(!(obj instanceof EasyUiPage)) {
            return false;
        } else {
            EasyUiPage that = (EasyUiPage)obj;
            boolean sortEqual = this.sort == null?that.sort == null:this.sort.equals(that.sort);
            return super.equals(that) && sortEqual;
        }
    }

    public int hashCode() {
        return 31 * super.hashCode() + (null == this.sort?0:this.sort.hashCode());
    }

    public String toString() {
        return String.format("Page request [number: %d, size %d, sort: %s]", new Object[]{Integer.valueOf(this.getPageNumber()), Integer.valueOf(this.getPageSize()), this.sort == null?null:this.sort.toString()});
    }
}
