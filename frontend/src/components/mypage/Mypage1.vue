<template>
<div>
    <!--top-->
    <!--right-->
    <div class="right">
        <div class="pList1">
            <h2>나의 쇼핑내역</h2>
        </div>
        <div class="date1">
            <div class="table1st">
                <div class="tables_header">조회기간</div>
                <div>
                    <div class="row1">
                        <button class="dateBtn">오늘</button>
                        <button class="dateBtn">1주일</button>
                        <button class="dateBtn">1개월</button>
                        <button class="dateBtn">6개월</button>
                        <button class="dateBtn">12월</button>
                        <button class="dateBtn">11월</button>
                        <button class="dateBtn">10월</button>
                    </div>
                    <div class="row2">
                        <!--기간별 주문/취소한 상품 내역 조회-->
                        <select name="" id="" @change="setStartYear">
                            <option v-for="n in years" :key="n" :value="n" :selected="n == getYear()">{{n}}</option>
                        </select>
                        <label for="">년</label>
                        <select name="" id="" @change="setStartMonth">
                            <option v-for="n in 12" :key="n" :value="n" :selected="n == getMonth()">{{n}}</option>
                        </select>
                        <label for="">월</label>
                        <select name="" id="" @change="setStartDate">
                            <option v-for="n in 31" :key="n" :value="n" :selected="n == getDate()">{{n}}</option>
                        </select>
                        <label for="">일</label>
                        -
                        <select name="" id="" @change="setEndYear">
                            <option v-for="n in years" :key="n" :value="n" :selected="n == getYear()">{{n}}</option>
                        </select>
                        <label for="">년</label>
                        <select name="" id="" @change="setEndMonth">
                            <option v-for="n in 12" :key="n" :value="n" :selected="n == getMonth()">{{n}}</option>
                        </select>
                        <label for="">월</label>
                        <select name="" id="" @change="setEndDate">
                            <option v-for="n in 31" :key="n" :value="n" :selected="n == getDate()">{{n}}</option>
                        </select>
                        <label for="">일</label>
                    </div>
                    <div class="row3">
                        <input type="text" class="searchBar" id="searchInput" placeholder="주문 상품명을 검색하세요!">
                        <button class="searchBtn" @click="search">조회하기</button>
                    </div>
                </div>
            </div>
        </div>

        <div class="pList2">
            <h2>주문내역 조회</h2>
            <!--주문내역 조회 테이블-->
        </div>
        <div>
            <div class="table1Header">
                <table style="width:1000px">
                    <tr style="height:50px;">
                        <td class="td1">주문일자</td>
                        <td class="td2">이미지</td>
                        <td class="td3">판매자명</td>
                        <td class="td4">상품명</td>
                        <td class="td5">옵션</td>
                        <td class="td6">수량</td>
                        <td class="td7">가격</td>
                        <td class="td8">배송비</td>
                    </tr>
                </table>
            </div>
            <div class="table1Body">
                <table style="width:1000px" v-for="test in tests" :key="test">
                    <!-- 날짜, 검색 정보를 비교하여 화면 목록에 표시 -->
                    <!-- v-show="compareDate(orderList) && compareInform(orderList) " style="height:50px;" -->
                    <tr v-for="order in orders" :key="order" >
                        <td class="td1">{{order.orderdate}}</td>
                        <!--<td class="td2">
                            <img :src="require(`@/assets/image/${order.img}`)" alt="banner" style="width:50px; height:50px;">
                        </td>-->
                        <td class="td3">{{order.id}}</td>
                        <td class="td4">{{test.productname}}</td>
                        <td class="td5">{{order.selectedoption}}</td>
                        <td class="td6">{{order.delivery}}</td>
                        <td class="td7">{{AddComma(order.totalprice)}}원</td>
                        <td class="td8" rowspan="1">{{order.delivery}}</td>
                    </tr>
                </table>
            </div>
        </div>
        <div class="pList3">
            <h2>취소/환불 조회</h2>
            <!--취소/환불 내역 조회 테이블-->
        </div>
        <div>
            <div class="table2Header">
                <table style="width:1000px">
                    <thead>
                        <tr class="tr1" style="height:50px;">
                            <td class="td1">주문일자</td>
                            <td class="td2">이미지</td>
                            <td class="td3">판매자명</td>
                            <td class="td4">상품명</td>
                            <td class="td5">옵션</td>
                            <td class="td6">가격</td>
                            <td class="td7">처리상태</td>
                            <td class="td8">결과</td>
                        </tr>
                    </thead>
                </table>
            </div>
            <div class="table2Body">
                <table style="width:1000px">
                    <tbody>
                        <!--v-show="compareDate(cancel) && compareInform(cancel) " class="tr1" style="height:50px;"-->
                        <tr v-for="cancel in cancels" :key="cancel">
                            <td class="td1">{{cancel.name}}</td>
                            <!--<td class="td2">
                                <img :src="require(`@/assets/image/${cancel.img}`)" alt="banner" style="width:50px; height:50px;">
                            </td>-->
                            <td class="td3">{{cancel.name}}</td>
                            <td class="td4">{{cancel.name}}</td>
                            <td class="td5">{{cancel.name}}</td>
                            <td class="td6">{{cancel.name}}</td>
                            <td class="td7">{{cancel.name}}</td>
                            <td class="td8">{{cancel.name}}</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
</template>

<script>
// import userList from "@/assets/data/orderList.json"; // 주문내역 제이슨
// import userList2 from "@/assets/data/refundList.json"; // 취소내역 제이슨
import axios from 'axios'
export default {
    data() {
        return {
            orders: [],
            cancels: [],
            tests:[],
            test:"",
            startPoint: {
                year: "1900",
                month: "01",
                date: "01",
            },
            endPoint: {
                year: this.getYear(),
                month: this.getMonth(),
                date: this.getDate(),
            },
            years: [],
            months: [],
            dates: [],
            target: "",

        }
    },
    // 주문/취소 내역 제이슨
    // name: "userList",
    // name2: "userList2",
    // computed: {
    //     users() {
    //         return userList.users.map((items) => {
    //             return items;
    //         })
    //     },
    //     users2() {
    //         return userList2.users2.map((items) => {
    //             return items;
    //         })
    //     }
    // },
    methods: {
        // 기간별 주문/취소한 상품 내역 조회를 위한 함수
        getDate() {
            let today = new Date();
            let date = today.getDate();
            return date;
        },
        getMonth() {
            let today = new Date();
            let month = today.getMonth() + 1;
            return month;
        },
        getYear() {
            let today = new Date();
            let year = today.getFullYear();
            return year;
        },
        setStartDate(event) {
            if (event.target.value < 10)
                this.startPoint.date = 0 + event.target.value;
            else
                this.startPoint.date = event.target.value;
        },
        setStartMonth(event) {
            if (event.target.value < 10)
                this.startPoint.month = 0 + event.target.value;
            else
                this.startPoint.month = event.target.value;
        },
        setStartYear(event) {
            this.startPoint.year = event.target.value;
        },
        setEndDate(event) {
            if (event.target.value < 10)
                this.endPoint.date = 0 + event.target.value;
            else
                this.endPoint.date = event.target.value;
        },
        setEndMonth(event) {
            if (event.target.value < 10)
                this.endPoint.month = 0 + event.target.value;
            else
                this.endPoint.month = event.target.value;
        },
        setEndYear(event) {
            this.endPoint.year = event.target.value;
        },
        //시작날짜와 끝날짜 기간사이 상품을 조회하기 위한 함수
        compareDate(target) {
            if (`${this.startPoint.year}-${this.startPoint.month}-${this.startPoint.orderDate}` <= target.orderDate)
                if (target.orderDate <= `${this.endPoint.year}-${this.endPoint.month}-${this.endPoint.date}`)
                    return true;
        },
        search() {
            this.target = document.getElementById("searchInput").value
        },
        //기간에 해당되는 상품 출력
        compareInform(event) {
            if (this.target == "")
                return true;
            else {
                if (event.product.indexOf(this.target) !== -1)
                    return true;
                else
                    return false;
            }
        },
        //가격부분 콤마 추가
        AddComma(num) {
            var regexp = /\B(?=(\d{3})+(?!\d))/g;
            return num.toString().replace(regexp, ",");
        },
        getData1 () {
                axios.get('api/order/all')
                .then((response) => {
                    this.orders = response.data
                })
            },
        getData2() {
            axios.get('/api/member/all')
                .then((response) => {
                    this.cancels = response.data
                })
        },
        getData3() {
            axios.get('api/product/productDetail/all')
                .then((response) => {
                    this.tests = response.data
                })
        },
        sendData() {
            axios.post("/test/send/hong", {
                send: "56475673"
            })
        }
    },
    mounted() {
        {
            for (let i = this.getYear() - 10; i <= this.getYear(); i++) {
                this.years.push(i);
            }
            this.year = this.getYear();
            this.month = this.getMonth();
            this.date = this.getDate();
        }
    },
    created() {
        this.getData1();
        this.getData2();
        this.getData3();
    },
}
</script>

<style scoped>
.table1Header,
.table2Header {
    margin-left: 30px;
    width: 95%;
    background-color: rgb(0, 153, 255)
}

.table1Header tr,
.table2Header tr {
    color: white;
}

.table1Body {
    margin-left: 30px;
    width: 95%;
    height: 300px;
    overflow-y: scroll;
}

.table2Body {
    margin-left: 30px;
    height: 300px;
    overflow-y: scroll;
}

.td1 {
    width: 100px
}

.td2 {
    width: 80px
}

.td3 {
    width: 80px
}

.td4 {
    width: 370px
}

.td5 {
    width: 90px
}

.td6 {
    width: 100px
}

.td7 {
    width: 100px
}

.td8 {
    width: 80px
}

.date1 {
    width: 1000px;
    display: flex;
    text-align: center;
    justify-items: center;
}

a {
    text-decoration: none;
    color: inherit;
    margin-left: 15px;
}

hr {
    width: 200px;
}

tr {
    border: inherit;
}

.top {
    margin-left: 70px;
}

.container {
    display: flex;
    border: none;
}

.left {
    display: inline-block;
    height: 1120px;
    border: 1px solid rgb(197, 195, 195);
    background-color: #fafafa;
    border-radius: 4px;
    width: 210px;
    padding: 15px;
    padding-top: 1px;
    font-size: 18px;
}

.right {
    display: inline-block;
    height: 1150px;
    width: 1100px;
    border: 1px solid rgb(197, 195, 195);
    margin-left: 20px;
    background-color: #fafafa;
    border-radius: 4px;
}

.pList1,
.pList2,
.pList3 {
    text-align: center;
    border-bottom: 1px solid rgb(197, 195, 195);

}

.date1,
.date2 {

    border-bottom: 1px solid rgb(197, 195, 195);
    padding-top: 20px;
    padding-bottom: 20px;
}

.date1 {
    display: flex;
}

.date2 {
    height: 300px;
    overflow-y: scroll;
}

.date3 {
    margin-top: 20px;
    padding-top: 15px;
    height: 300px;
    overflow-y: scroll;
}

.table1st {
    margin-left: auto;
    margin-right: auto;
    display: flex;

}

.tables_header {
    width: 140px;
    height: 100px;
    padding-top: 20px;
    text-align: center;
    line-height: 80px;
    font-weight: bold;
    border: 1px solid;
    border-radius: 4px;
    background-color: #00ba54;
    color: white;
    margin-right: 20px;
}

.searchBtn {
    margin-left: 20px;
}

.row1,
.row2 {
    padding: 1px;
    border-radius: 4px;
}

.row2 {
    margin-left: 6px;
}

.row3 {
    display: flex;
    padding: 5px 0px;
}

.searchBar {
    width: 400px;
    margin-left: auto;
    margin-right: 1px;
    border-radius: 4px;
    border: 1px solid;
    padding: 20px;
}

.List1 {
    text-align: center;
    padding-top: 15px;
    padding-bottom: 10px;
    margin-right: 20px;
    font-size: 22px;
}

.List2 {
    text-align: center;
    padding-top: 70px;
    padding-bottom: 10px;
    margin-right: 20px;
    font-size: 22px
}

.Check {
    margin-left: 20px;
    color: rgb(129, 124, 124);
    border-color: inherit;
    line-height: 35px;
    font-size: 18px
}

.Check li {
    width: 150px;
    padding: 0;
    margin: 10px 0px;
}

.dateBtn {
    border-radius: 4px;
    border: none;
    color: white;
    padding: 5px 10px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 13px;
    cursor: pointer;
    margin: 0px 1px;
    margin: 0px 5px;

}

/*.tr1 {
    background-color: rgb(0, 153, 255);
    color: white;
}*/

#edge1 {
    border-top-left-radius: 8px;
}

#edge2 {
    border-top-right-radius: 8px;
}
</style>
