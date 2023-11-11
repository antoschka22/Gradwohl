import { Directive, ElementRef, Renderer2, HostListener } from '@angular/core';

@Directive({
  selector: '[appEditable]'
})
export class EditableDirective {
  constructor(private el: ElementRef, private renderer: Renderer2) {}

  private editing = false;

  @HostListener('click', ['$event'])
  onClick(event: MouseEvent) {
    if (!this.editing) {
      this.selectSegment(event);
      this.editing = true;
    }
  }

  @HostListener('focusout')
  onFocusOut() {
    this.editing = false;
    this.clearSelection();
  }

  private selectSegment(event: MouseEvent) {
    const selection = window.getSelection();
    if (selection) {
      const range = document.createRange();
      range.selectNodeContents(this.el.nativeElement);
      selection.removeAllRanges();
      selection.addRange(range);
      const text = this.el.nativeElement.textContent || '';
      const caretPosition = this.getCaretPosition(event);
      if (caretPosition >= 0 && caretPosition < text.length) {
        const selectedSegment = text[caretPosition] + text[caretPosition + 1];
        const newText = text.replace(selectedSegment, `<mark>${selectedSegment}</mark>`);
        this.el.nativeElement.innerHTML = newText;
      }
    }
  }

  private clearSelection() {
    const selection = window.getSelection();
    if (selection) {
      selection.removeAllRanges();
      this.removeMarkElements();
    }
  }

  private removeMarkElements() {
    const markElements = this.el.nativeElement.querySelectorAll('mark');
    markElements.forEach((element: HTMLElement) => {
      const content = element.textContent;
      const parent = element.parentElement;
      if (parent && content) {
        parent.replaceChild(document.createTextNode(content), element);
      }
    });
  }

  private getCaretPosition(event: MouseEvent): number {
    const range = document.createRange();
    const selection = window.getSelection();
    if (selection) {
      range.setStart(this.el.nativeElement, 0);
      range.setEnd(event.target as Node, 0);
      range.collapse(true);
      if (selection?.rangeCount) {
        const rect = range.getClientRects()[0];
        if (rect) {
          return this.getCaretPositionFromRect(rect, event);
        }
      }
    }
    return -1;
  }

  private getCaretPositionFromRect(rect: DOMRect, event: MouseEvent): number {
    const clickX = event.clientX;
    let charIndex = 0;
    let cumulativeWidth = 0;
    while (charIndex < this.el.nativeElement.textContent.length) {
      cumulativeWidth += this.getWidthOfCharacter(this.el.nativeElement.textContent[charIndex]);
      if (cumulativeWidth >= clickX - rect.left) {
        return charIndex;
      }
      charIndex++;
    }
    return charIndex;
  }

  private getWidthOfCharacter(character: string): number {
    const charElement = document.createElement('span');
    charElement.style.display = 'inline-block';
    charElement.style.font = 'inherit';
    charElement.style.fontSize = 'inherit';
    charElement.style.whiteSpace = 'nowrap';
    charElement.textContent = character;
    document.body.appendChild(charElement);
    const width = charElement.getBoundingClientRect().width;
    document.body.removeChild(charElement);
    return width;
  }
}
