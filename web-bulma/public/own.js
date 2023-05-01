document.addEventListener('DOMContentLoaded', () => {

  // Get all "navbar-burger" elements
  const $navbarBurgers = Array.prototype.slice.call(document.querySelectorAll('.navbar-burger'), 0);
  const $navbarItems = Array.prototype.slice.call(document.querySelectorAll('.navbar-menu'), 0);

  // Check if there are any navbar burgers
  if ($navbarBurgers.length > 0) {

    // Add a click event on each of them
    $navbarBurgers.forEach( el => {
      el.addEventListener('click', () => {

        // Get the target from the "data-target" attribute
        const target = el.dataset.target;
        const $target = document.getElementById(target);

        // Toggle the "is-active" class on both the "navbar-burger" and the "navbar-menu"
        el.classList.toggle('is-active');
        $target.classList.toggle('is-active');

      });
    });
  }
  if ($navbarItems.length > 0) {
      // Add a click event on each of them
      $navbarItems.forEach( el => {
        el.addEventListener('click', () => {
            const targetId = el.id;
            $navbarBurgers.forEach( burger => {
                if(burger.dataset.target == targetId){
                    burger.classList.toggle('is-active');
                }
            });
           el.classList.toggle('is-active');
        });
      });
   }
});