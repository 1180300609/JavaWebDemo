import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from "../components/Login";
import Home from "../components/Home";
import Menu from "../components/Menu";
import Welcome from "../components/Welcome";
import Order from "../components/Order";
import AddMenu from "../components/AddMenu";
import AddOrder from "../components/AddOrder";
import User from "../components/User";
import AddUser from "../components/AddUser";
import HomeCustomer from "../components/HomeCustomer";
import HomeCook from "../components/HomeCook";
import OrderCustomer from "../components/OrderCustomer";
import MenuCustomer from "../components/MenuCustomer";
import AddOrderCustomer from "../components/AddOrderCustomer";
import CustomerUpdate from "../components/CustomerUpdate";
import MenuCook from "../components/MenuCook";
import CookUpdate from "../components/CookUpdate";
import OrderCook from "../components/OrderCook";
import OrderCookGet from "../components/OrderCookGet";

Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        redirect: '/login'
    },
    {
        path: '/login',
        component: Login
    },
    {
        path: '/home',
        component: Home,
        redirect: '/welcome',
        children: [
            {
                path: '/welcome',
                component: Welcome
            },
            {
                path: '/menu',
                component: Menu
            },
            {
                path: '/addMenu',
                component: AddMenu
            },
            {
                path: '/order',
                component: Order
            },
            {
                path: '/addOrder',
                component: AddOrder
            },
            {
                path: '/user',
                component: User
            },
            {
                path: '/addUser',
                component: AddUser
            }
        ]
    },
    {
        path: '/homeCustomer',
        component: HomeCustomer,
        children: [
            {
                path: '/menuCustomer',
                component: MenuCustomer
            },
            {
                path: '/orderCustomer',
                component: OrderCustomer
            },
            {
                path: '/addOrderCustomer',
                component: AddOrderCustomer
            },
            {
                path: '/customerUpdate',
                component: CustomerUpdate
            }
        ]
    },
    {
        path: '/homeCook',
        component: HomeCook,
        children: [
            {
                path: '/menuCook',
                component: MenuCook
            },
            {
                path: '/cookUpdate',
                component: CookUpdate
            },
            {
                path: '/orderCook',
                component: OrderCook
            },
            {
                path: '/orderCookGet',
                component: OrderCookGet
            }
        ]
    }

]

const router = new VueRouter({
    routes
})

router.beforeEach((to, from, next) => {
    if (to.path === '/login') return next();
    let item = window.sessionStorage.getItem('token');
    if (!item) {
        return next('/login');
    }
    next();
})

export default router
