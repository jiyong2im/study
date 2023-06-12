package com.springboot.boardtest.data.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pagination {


        private String display;
        private int pageNo;
        private boolean curPage;

        public Pagination(String display, int pageNo, boolean curPage) {
                this.display = display;
                this.pageNo = pageNo;
                this.curPage = curPage;
        }

        public Pagination() {
        }

        public static PaginationBuilder builder() {
                return new PaginationBuilder();
        }


        public static class PaginationBuilder {
                private String display;
                private int pageNo;
                private boolean curPage;

                PaginationBuilder() {
                }

                public PaginationBuilder display(String display) {
                        this.display = display;
                        return this;
                }

                public PaginationBuilder pageNo(int pageNo) {
                        this.pageNo = pageNo;
                        return this;
                }

                public PaginationBuilder curPage(boolean curPage) {
                        this.curPage = curPage;
                        return this;
                }

                public Pagination build() {
                        return new Pagination(this.display, this.pageNo, this.curPage);
                }

                public String toString() {
                        return "Pagination.PaginationBuilder(display=" + this.display + ", pageNo=" + this.pageNo + ", curPage=" + this.curPage + ")";
                }
        }
}

