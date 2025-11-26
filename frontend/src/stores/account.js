import { ref, computed } from 'vue'
import { defineStore } from 'pinia' 
   // 피니아에서 제공하는 defineStore 메서드를 호출해서 스토어를 정의하였다. 
   // 계정 데이터를 다루는 계정 스토어

export const useAccountStore = defineStore("account", { // ① "account" 스토어의 고유아이디
    state: () => ({
        checked: false, // ② 사용자의 로그인 체크 여부 프로퍼티
        loggedIn: false, // ③ 사용자의 로그인 여부 프로퍼티
    }),
    actions: {
        setChecked(val) { // ④   사용자의 로그인 체크 여부 값을 수정하는 메서드
            this.checked = val;
        },
        setLoggedIn(val) { // ⑤ 사용자의 로그인 여부 값을 수정하는 메서드
            this.loggedIn = val;
        },
    },
});
