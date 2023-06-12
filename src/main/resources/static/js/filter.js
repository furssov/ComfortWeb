const filterCard = document.querySelectorAll('.card');

document.querySelector('nav').addEventListener('click', () => {
        if (event.target.tagName !== 'BUTTON') return false;

        let filterClass = event.target.dataset['f'];

        filterCard.forEach((element) => {
                if (!element.classList.contains(filterClass) && filterClass != 'all') {
                        element.classList.add('hidden'); // Добавление класса "hidden" для скрытия элемента
                } else {
                        element.classList.remove('hidden'); // Удаление класса "hidden" для отображения элемента
                }
        });
});
