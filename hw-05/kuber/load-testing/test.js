import http from 'k6/http';
import { Counter } from 'k6/metrics';

export const status2xx = new Counter('status_2xx');
export const status4xx = new Counter('status_4xx');
export const status5xx = new Counter('status_5xx');

export const options = {
  scenarios: {
    ramping_test: {
      executor: 'ramping-vus', // Тип сценария
      startVUs: 0,             // Начальное количество VUs
      stages: [
        { duration: '1m', target: 10 }, // За 1 минуту увеличится до 10 VUs
        { duration: '1m', target: 20 }, // За следующие 3 минуты увеличится до 50 VUs
        { duration: '1m', target: 30 }, // За следующие 3 минуты увеличится до 50 VUs
        { duration: '4m', target: 50 }, // За следующие 3 минуты увеличится до 50 VUs
        { duration: '1m', target: 60 },  // За 2 минуты уменьшится обратно до 0 VUs
        { duration: '2m', target: 0 },  // За 2 минуты уменьшится обратно до 0 VUs
      ],
      gracefulRampDown: '2m',          // Время на плавное завершение VUs
    },
  },
};


export default function () {
  let payload = JSON.stringify({
    login: 'test',
  });

  let headers = {
    'Content-Type': 'application/json',
  };

  let host = 'http://arch.homework';
  // let host = 'http://localhost:8080';

  // http.post(host + '/users', payload, { headers });
  // http.put(host + '/users/141036', payload, { headers });
  // http.get(host + '/users/141035');


  // http.post(host + '/users', payload, { headers });
  // http.put(host + '/users/1', payload, { headers });
  const res = http.get(host + '/users/1');


  if (res.status >= 200 && res.status < 300) {
    status2xx.add(1);
  } else if (res.status >= 400 && res.status < 500) {
    status4xx.add(1);
  } else if (res.status >= 500) {
    status5xx.add(1);
  }
}