import { Directive, ElementRef, HostListener } from '@angular/core';

@Directive({
  selector: '[appEditable]'
})
export class EditableDirective {
  constructor(private el: ElementRef) {}

  @HostListener('click') onClick() {
    const divElement = this.el.nativeElement;
    divElement.contentEditable = 'true';

    divElement.addEventListener('focus', () => {
      // Store the initial text content for comparison
      divElement.dataset.initialText = divElement.textContent;

      //Format ändern wenn der String leer ist
      if (divElement.textContent.trim() === '' || divElement.textContent.trim() === 'URLAUB') {
        divElement.textContent = '00:00 - 00:00';
      }
    });





    divElement.addEventListener('blur', () => {
      let initialText = divElement.dataset.initialText;
      const updatedText = this.formatTimeRange(divElement.textContent);
      let testTextForDeletion: boolean = false;
      let urlaub: boolean = false


      if(divElement.textContent.trim() === 'u' 
      || divElement.textContent.trim() === 'urlaub'
      || divElement.textContent.trim() === 'URLAUB'){
        divElement.textContent = "URLAUB"
        initialText = "URLAUB"
        urlaub = true;
        const event = new CustomEvent('edit-done', {
          detail: {
            textContent: initialText,
            neu: false,
            delete: false,
            initialText: initialText,
            urlaub: true
          }
        });
        divElement.dispatchEvent(event);
      }

      console.log(initialText, updatedText, divElement.textContent.trim())
      // delete
      if((this.isValidTimeFormat(initialText.trim()) || (initialText.trim() === 'URLAUB')) && updatedText.trim() == '' && !urlaub){
        console.log("editable delete", initialText.trim(), updatedText)
        const event = new CustomEvent('edit-done', {
          detail: {
            textContent: initialText,
            neu: false,
            delete: true,
            initialText: initialText,
            urlaubt: false
          }
        });
        divElement.dispatchEvent(event);
        divElement.textContent = "";
        testTextForDeletion = true;
      }

      if (initialText !== updatedText && !testTextForDeletion && !urlaub) {
        // Format validieren
        if (this.isValidTimeFormat(updatedText.trim())) {
          divElement.textContent = updatedText;

          //insert
          if(initialText.trim() == '' || initialText.trim() == '00:00 - 00:00'){
            const event = new CustomEvent('edit-done', {
              detail: {
                textContent: divElement.textContent,
                neu: true,
                delete: false,
                initialText: initialText,
                urlaubt: false
              }
            });
            divElement.dispatchEvent(event);
          }else{
            //update
            const event = new CustomEvent('edit-done', {
              detail: {
                textContent: divElement.textContent,
                neu: false,
                delete: false,
                initialText: initialText,
                urlaubt: false
              }
            });
            divElement.dispatchEvent(event);
          }
          
        } else {
          divElement.textContent = initialText;
        }
      }

      if (divElement.textContent.trim() === '00:00 - 00:00' || divElement.textContent.trim() === '') {
        divElement.textContent = String.fromCharCode(160) + String.fromCharCode(160) + String.fromCharCode(160) + String.fromCharCode(160);
      }

      divElement.contentEditable = 'false';
    });
  }

  isValidTimeFormat(text: string): boolean {
    // test format (hh:mm - hh:mm)
    const regex = /^(\d{2}:\d{2} - \d{2}:\d{2})$/;
  
    //format "hh:mm - hh:mm" testen
    if (!regex.test(text)) {
      return false;
    }
  
    const [startTime, endTime] = text.split(" - ");
  
    // Uhrzeit darf con 4 bis 20 uhr sein
    const startTimeRegex = /^(0[4-9]|1[0-9]|20):[0-5][0-9]$/;
    const endTimeRegex = /^(0[4-9]|1[0-9]|20):[0-5][0-9]$/;
    
    if (!startTimeRegex.test(startTime) || !endTimeRegex.test(endTime)) {
      return false;
    }
  
    // Convert start and end times to Date objects for comparison
    const startTimeParts = startTime.split(":");
    const endTimeParts = endTime.split(":");
    const startDateTime = new Date(0, 0, 0, parseInt(startTimeParts[0]), parseInt(startTimeParts[1]));
    const endDateTime = new Date(0, 0, 0, parseInt(endTimeParts[0]), parseInt(endTimeParts[1]));
  
    // Check if the start time is before the end time
    return startDateTime < endDateTime;
  }

  formatTimeRange(timeRange: string): string {
    if(timeRange.trim() === "" || timeRange.trim() === "URLAUB" || timeRange.trim() === 'u' || timeRange.trim() === 'urlaub'){
      return timeRange
    }
    const [start, end] = timeRange.split(' - ');
  
    const formatTimePart = (timePart: string): string => {
      if(timePart != null){
        const [hours, minutes] = timePart.split(':').map(Number);
  
        const formattedHours = hours < 10 ? `0${hours}` : `${hours}`;
        const formattedMinutes = minutes < 10 ? `0${minutes}` : `${minutes}`;
    
        return `${formattedHours}:${formattedMinutes}`;
      }else{
        return ""
      }
    };
  
    const formattedStart = formatTimePart(start);
    const formattedEnd = formatTimePart(end);
  
    return `${formattedStart} - ${formattedEnd}`;
  }
  
}