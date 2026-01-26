/**
 * API helper object for making REST calls
 */
const API = {
    async get(url) {
        const response = await fetch(url);
        if (!response.ok) throw new Error(await response.text() || 'Request failed');
        return response.json();
    },

    async post(url, data) {
        const response = await fetch(url, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(data)
        });
        if (!response.ok) throw new Error(await response.text() || 'Request failed');
        return response.text();
    }
};
