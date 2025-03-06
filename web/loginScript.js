document
                .getElementById('target')
                .addEventListener('change', function () {
                    'use strict';
                    var vis = document.querySelector('.vis'),   
                        target = document.getElementById(this.value);
                    if (vis !== null) {
                        vis.className = 'inv';
                    }
                    if (target !== null ) {
                        target.className = 'vis';
                    }
            });

//make EL message disappear when user click anything on the page
document.addEventListener('DOMContentLoaded', function() {
  const elContent = document.getElementById('error');

  document.addEventListener('click', function(event) {
    // Check if the click occurred outside the elContent div
    if (error && !error.contains(event.target)) {
      elContent.style.display = 'none'; // Hide the content
    }
  });
});
