//GRAB THE ELEMENTS WE NEED TO CONTROL
const tabs = document.querySelectorAll('.tab'); // returns ALL matching elements (a list)
const input = document.getElementById('username'); // returns ONE element
const runBtn = document.getElementById('runBtn');
const statusEl = document.getElementById('status');
const report = document.getElementById('report');

//STATE
let platform = 'github';

//WIRE UP THE TABS
tabs.forEach(t => t.addEventListener('click', () => {
    // Remove "active" class from every tab first...
    tabs.forEach(x => x.classList.remove('active'));
    // ...then add it back only to the one that was clicked.
    t.classList.add('active');
    // data-platform="github" in the HTML is read here as t.dataset.platform
    platform = t.dataset.platform;
}));

//FIELD CONFIG
const FIELDS = {
    github: [
        ['totalRepos', 'Repos'], ['totalStars', 'Stars'],
        ['avgStars', 'Avg Stars'], ['followers', 'Followers']
    ],
    leetcode: [
        ['ranking', 'Global Rank'], ['easySolved', 'Easy'],
        ['mediumSolved', 'Medium'], ['hardSolved', 'Hard'],
        ['totalSolved', 'Total Solved'], ['streak', 'Streak (days)'],
        ['badgeCount', 'Badges']
    ],
    codeforces: [
        ['currRating', 'Current Rating'], ['maxRating', 'Max Rating'],
        ['friends', 'Friends'], ['totalActive', 'Active Days']
    ]
};

//ESCAPE USER-FACING TEXT
function escapeHtml(str) {
    return String(str).replace(/[&<>"']/g, c => ({
        '&': '&amp;', '<': '&lt;', '>': '&gt;', '"': '&quot;', "'": '&#39;'
    }[c]));
}

//BUILD THE REPORT CARD
function render(platform, username, data) {
    // .map() transforms each [key, label] pair into one line of HTML,
    // then .join('') glues all those lines into a single string.
    const rows = FIELDS[platform]
        .map(([key, label]) =>
            // data[key] does the same thing as data.get(key) would in Java —
            // it looks up that field on the object dynamically.
            // "?? '—'" means: if data[key] is null/undefined, show a dash instead.
            `<div class="stat"><span class="k">${label}</span><span class="v">${data[key] ?? '—'}</span></div>`
        )
        .join('');

    // Template literals (the backtick string below) let you embed variables
    // directly with ${...} — like String.format in Java, but built into the syntax.
    report.innerHTML = `
    <div class="report-head">Subject <b>${escapeHtml(username)}</b> — ${platform}</div>
    ${rows}
    <div class="verdict">
      <div class="verdict-label">Recruiter's Verdict</div>
      <div class="verdict-text">${escapeHtml(data.critique || 'No critique returned.')}</div>
    </div>
  `;

    report.style.display = 'block';

    // Restart the CSS reveal animation every time a new report renders.
    report.classList.remove('show');
    void report.offsetHeight; // forces the browser to "notice" the class was removed
    report.classList.add('show');
}

//TALK TO THE BACKEND

// pause execution until a Promise (an eventual result) resolves,
// without freezing the whole page.
async function analyze() {
    const username = input.value.trim();
    if (!username) {
        statusEl.textContent = 'Enter a username first.';
        statusEl.className = 'status error';
        return;
    }

    runBtn.disabled = true;
    statusEl.className = 'status';
    statusEl.textContent = 'Reviewing case file…';
    report.style.display = 'none';
    const API_BASE = 'http://DevAnalyzerBackend.onrender.com';
    try {

        const res = await fetch(`${API_BASE}/analyze/${platform}/${encodeURIComponent(username)}`);


        const data = await res.json();

        // res.ok is true only for 2xx status codes.GlobalExceptionHandler
        // sends back 404/500 with an ErrorResponse shape ({ message: "..." }) —
        // this is where that gets displayed instead of crashing the page.
        if (!res.ok) {
            statusEl.className = 'status error';
            statusEl.textContent = data.message || 'Something went wrong.';
            return;
        }

        statusEl.textContent = '';
        render(platform, username, data);

    } catch (err) {
        // This catch fires if the request never even reached the server —
        // e.g. Spring Boot isn't running at all. Different from a 404/500,
        // which DO reach the server and come back as a normal response.
        statusEl.className = 'status error';
        statusEl.textContent = 'Could not reach the server. Is it running?';

    } finally {
        // finally always runs, whether the try succeeded or the catch fired —
        // guarantees the button re-enables no matter what happened.
        runBtn.disabled = false;
    }
}

//HOOK UP THE TRIGGERS
runBtn.addEventListener('click', analyze);
// Also let Enter key trigger the same function while focused in the input box.
input.addEventListener('keydown', e => {
    if (e.key === 'Enter') analyze();
});