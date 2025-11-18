const API_BASE = "http://localhost:8080/api";

async function apiCall(method, url, data = null) {
    const options = {
        method: method,
        headers: { "Content-Type": "application/json" }
    };
    if (data) options.body = JSON.stringify(data);

    const res = await fetch(API_BASE + url, options);
    return res.json();
}
