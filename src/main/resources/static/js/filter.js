

function app()
{
    const buttons = document.querySelectorAll('.btn')
    const cards = document.querySelectorAll('.card')

    function filter(items, category)
    {

        items.forEach((item) => {
            const isItemContainsCategory = !item.classList.contains(category)
            const isShowAll = category.toLowerCase() === 'all'
            if (isItemContainsCategory && !isShowAll)
            {
                item.classList.add('anim')
            }
            else
            {
                item.classList.remove('hide')
                item.classList.remove('anim')
            }
        })
    }

    buttons.forEach(
        (button) =>{
            button.addEventListener('click', () => {
                const currentCategory = button.dataset.filter
                filter(cards, currentCategory)
            })
        })

    cards.forEach((card) =>
    {
        card.ontransitionend = function ()
        {
            if (card.classList.contains('anim')) {
                card.classList.add('hide')
            }
        }
    })
}

app()