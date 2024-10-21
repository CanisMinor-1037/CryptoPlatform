import Vue from "vue";
import VueRouter from "vue-router";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    redirect: "login",
  },
  {
    path: "/login",
    name: "login",
    component: () => import("@/views/login"),
  },
  {
    path: "",
    name: "main",
    component: () => import("@/views/Main"),
    children: [
      {
        path: "/index",
        name: "index",
        component: () => import("@/views/index"),
      },
      {
        path: "/confirmation",
        name: "confirmation",
        component: () => import("@/views/confirmation"),
      },
      {
        path: "/confirmation2",
        name: "confirmation2",
        component: () => import("@/views/confirmation2"),
      },
      {
        path: "/confirmation3",
        name: "confirmation3",
        component: () => import("@/views/confirmation3"),
      },
      {
        path: "/order",
        name: "order",
        component: () => import("@/views/order"),
      },
      {
        path: "/order2",
        name: "order2",
        component: () => import("@/views/order2"),
      },
      {
        path: "/order3",
        name: "order3",
        component: () => import("@/views/order3"),
      },
      {
        path: "/attribute",
        name: "attribute",
        component: () => import("@/views/attribute"),
      },
       {
        path: "/camera",
        name: "camera",
        component: () => import("@/views/camera"),
      },
      {
        path: "/database",
        name: "database",
        component: () => import("@/views/database"),
      }
      
    ],
  },
];

const router = new VueRouter({
  routes,
});



export default router;
