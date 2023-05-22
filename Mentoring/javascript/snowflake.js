const snowflakesContainer = document.querySelector('.snow_wrap');

function createSnowflake() {
    const snowFlake = document.createElement('div');
    snowFlake.classList.add('snowflake');
    snowFlake.innerHTML = '❆';
    snowFlake.style.left = Math.random() * window.innerWidth + 'px';
    snowFlake.style.animationDuration = Math.random() * 0.3 + 2 + 's';
    snowFlake.style.opacity = Math.random();
    snowFlake.style.fontSize = Math.random() * 10 + 10 + 'px';

    snowflakesContainer.appendChild(snowFlake);

    setTimeout(() => {
        snowFlake.remove();
    }, 5000);
}

setInterval(createSnowflake, 50);