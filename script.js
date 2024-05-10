import http from 'k6/http';
import { sleep } from 'k6';

export let options = {
    stages: [
        { duration: '1s', target: 2 } // 2 RPS for 1 s
    ],
    thresholds: {
        http_req_duration: ['p(95)<500'], // 95% of requests must complete within 500ms
    },
};

export default function () {
    // Make a GET request to the specified URL
    let response = http.get('http://192.168.50.4:8089/SpringMVC/categorieProduit/retrieve-all-categorieProduit');

    // Check if the response status is 200 (OK)
    if (response.status !== 200) {
        console.error(`Request failed with status: ${response.status}`);
    }

}
