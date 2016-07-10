/* 
 Author     : Tomaz Dragar
 Mail       : <tomaz@dragar.net>
 Homepage   : http://www.dragar.net
 */

(function ($) {

    $.fn.simpleCropper = function (uploadServlet) {
        /*MAJID*/
        //$(this).css("position", "relative");
        /*END MAJID*/
        var image_dimension_x = 600;
        var image_dimension_y = 600;
        var scaled_width = 0;
        var scaled_height = 0;
        var x1 = 0;
        var y1 = 0;
        var x2 = 0;
        var y2 = 0;
        var current_image = null;
        var aspX = 1;
        var aspY = 1;
        var file_display_area = null;
        var ias = null;
        var jcrop_api;
        var bottom_html = "<input type='file' id='fileInput' name='files[]'/><canvas id='myCanvas' style='display:none;'></canvas><div id='modal'></div><div id='preview'><div class='buttons'><div class='cancel'></div><div class='ok'></div></div></div>";
        $('body').append(bottom_html);


        //add click to element
        this.click(function () {
            aspX = $(this).width();
            aspY = $(this).height();
            file_display_area = $(this);
            $('#fileInput').click();
        });

        $(document).ready(function () {
            //capture selected filename
            $('#fileInput').change(function (click) {
                imageUpload($('#preview').get(0));
                // Reset input value
                $(this).val("");
            });

            //ok listener
            $('.ok').click(function () {
                preview();
                $('#preview').delay(100).hide();
                $('#modal').hide();
                jcrop_api.destroy();
                reset();
            });

            //cancel listener
            $('.cancel').click(function (event) {
                $('#preview').delay(100).hide();
                $('#modal').hide();
                jcrop_api.destroy();
                reset();
            });
        });

        function reset() {
            scaled_width = 0;
            scaled_height = 0;
            x1 = 0;
            y1 = 0;
            x2 = 0;
            y2 = 0;
            current_image = null;
            aspX = 1;
            aspY = 1;
            file_display_area = null;
        }

        function imageUpload(dropbox) {
            var file = $("#fileInput").get(0).files[0];
            //var file = document.getElementById('fileInput').files[0];
            var imageType = /image.*/;

            if (file.type.match(imageType)) {
                var reader = new FileReader();

                reader.onload = function (e) {
                    // Clear the current image.
                    $('#photo').remove();

                    // Create a new image with image crop functionality
                    current_image = new Image();
                    current_image.src = reader.result;
                    current_image.id = "photo";
                    current_image.style['maxWidth'] = image_dimension_x + 'px';
                    current_image.style['maxHeight'] = image_dimension_y + 'px';
                    current_image.onload = function () {
                        // Calculate scaled image dimensions
                        if (current_image.width > image_dimension_x || current_image.height > image_dimension_y) {
                            if (current_image.width > current_image.height) {
                                scaled_width = image_dimension_x;
                                scaled_height = image_dimension_x * current_image.height / current_image.width;
                            }
                            if (current_image.width < current_image.height) {
                                scaled_height = image_dimension_y;
                                scaled_width = image_dimension_y * current_image.width / current_image.height;
                            }
                            if (current_image.width == current_image.height) {
                                scaled_width = image_dimension_x;
                                scaled_height = image_dimension_y;
                            }
                        }
                        else {
                            scaled_width = current_image.width;
                            scaled_height = current_image.height;
                        }


                        // Position the modal div to the center of the screen
                        $('#modal').css('display', 'block');
                        var window_width = $(window).width() / 2 - scaled_width / 2 + "px";
                        var window_height = $(window).height() / 2 - scaled_height / 2 + "px";

                        // Show image in modal view
                        $("#preview").css("top", window_height);
                        $("#preview").css("left", window_width);
                        $('#preview').show(500);


                        // Calculate selection rect
                        var selection_width = 0;
                        var selection_height = 0;

                        var max_x = Math.floor(scaled_height * aspX / aspY);
                        var max_y = Math.floor(scaled_width * aspY / aspX);


                        if (max_x > scaled_width) {
                            selection_width = scaled_width;
                            selection_height = max_y;
                        }
                        else {
                            selection_width = max_x;
                            selection_height = scaled_height;
                        }

                        ias = $(this).Jcrop({
                            onSelect: showCoords,
                            onChange: showCoords,
                            bgColor: '#747474',
                            bgOpacity: .4,
                            aspectRatio: aspX / aspY,
                            setSelect: [0, 0, selection_width, selection_height]
                        }, function () {
                            jcrop_api = this;
                        });
                    };

                    // Add image to dropbox element
                    dropbox.appendChild(current_image);
                };
                reader.readAsDataURL(file);

            } else {
                dropbox.innerHTML = "File not supported!";
            }
        }

        function showCoords(c) {
            x1 = c.x;
            y1 = c.y;
            x2 = c.x2;
            y2 = c.y2;
        }

        function preview() {
            // Set canvas
            var canvas = document.getElementById('myCanvas');
            var context = canvas.getContext('2d');

            // Delete previous image on canvas
            context.clearRect(0, 0, canvas.width, canvas.height);

            // Set selection width and height
            var sw = x2 - x1;
            var sh = y2 - y1;


            // Set image original width and height
            var imgWidth = current_image.naturalWidth;
            var imgHeight = current_image.naturalHeight;

            // Set selection koeficient
            var kw = imgWidth / $("#preview").width();
            var kh = imgHeight / $("#preview").height();

            // Set canvas width and height and draw selection on it
            canvas.width = aspX;
            canvas.height = aspY;
            var ww = (sw * kw);
            if (ww > imgWidth) ww = imgWidth;
            var hh = (sh * kh);
            if (hh > imgHeight) hh = imgHeight;
            context.drawImage(current_image, (x1 * kw), (y1 * kh), ww, hh, 0, 0, aspX, aspY);

            // Convert canvas image to normal img
            var dataUrl = canvas.toDataURL();
            var imageFoo = document.createElement('img');
            imageFoo.src = dataUrl;

            // Append it to the body element
            $('#preview').delay(100).hide();
            $('#modal').hide();
            file_display_area.html('');
            file_display_area.append(imageFoo);

            //MAJID
            $(file_display_area).css("position", "relative");
            $(file_display_area).css("text-align", "center");
            var progressElement = $("<div class='progress-container'/>");
            file_display_area.append(progressElement);

            var progressUUID = generateUUID();
            var progressValue = $("<div class='progress-value' />");
            progressValue.attr("id", progressUUID);
            progressElement.append(progressValue);

            var filenameUUID = generateUUID();
            var filenameElement = $("<div class='progress-file-name'/>");
            filenameElement.attr("id", filenameUUID);
            progressElement.append(filenameElement);

            var hiddenUUID = generateUUID();
            var hiddenElement = $("<input type='hidden'/>");
            hiddenElement.attr("id", hiddenUUID);
            hiddenElement.attr("name", file_display_area.data("name"));
            file_display_area.append(hiddenElement);

            //TODO male hoseino bezaram inja
            function generateUUID() {
                var d = new Date().getTime();
                var uuid = 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function (c) {
                    var r = (d + Math.random() * 16) % 16 | 0;
                    d = Math.floor(d / 16);
                    return (c == 'x' ? r : (r & 0x3 | 0x8)).toString(16);
                });
                return uuid;
            };

            $.ajax({
                url: uploadServlet,
                type: 'POST',
                //data: {image: canvas.toDataURL()},
                data: {image: canvas.toDataURL("image/jpeg")},
                timeout: 600000,
                cache: false,
                error: function (xhr, ajaxOptions, thrownError) {
                    alert(xhr.responseText);
                    alert(thrownError);
                },
                xhr: function () {
                    var xhr = new window.XMLHttpRequest();
                    //Download progress
                    xhr.addEventListener("progress", function (evt) {
                        if (evt.lengthComputable) {
                            var percentComplete = evt.loaded / evt.total;
                            var progress = Math.round(percentComplete * 100) + "%";
                            $(progressValue).css("width", progress);
                        }
                    }, false);
                    return xhr;
                },
                success: function (json) {
                    $(filenameElement).text(json);
                    $(hiddenElement).val(json);
                }
            });

        }

        $(window).resize(function () {
            // Position the modal div to the center of the screen
            var window_width = $(window).width() / 2 - scaled_width / 2 + "px";
            var window_height = $(window).height() / 2 - scaled_height / 2 + "px";

            // Show image in modal view
            $("#preview").css("top", window_height);
            $("#preview").css("left", window_width);
        });
    };

    var upload = function (canvas) {
        $.ajax({
            url: '/controller/saveImage',
            type: 'POST',
            data: {image: canvas.toDataURL("image/jpeg;base64;", 0.5)},
            success: function (result) {
                alert(result);
            }
        });
    }
}(jQuery));
