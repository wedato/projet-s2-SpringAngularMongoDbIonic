import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {LoadingController, ToastController} from "@ionic/angular";
import jsQR from "jsqr";
import {FicheEmargementService} from "../fiche-emargement.service";
import {FicheEmargement} from "../fiche-emargement.model";

@Component({
  selector: 'app-fiche-scan',
  templateUrl: './fiche-scan.page.html',
  styleUrls: ['./fiche-scan.page.scss'],
})
export class FicheScanPage implements OnInit {

  @ViewChild('video', { static: false }) video: ElementRef;
  @ViewChild('canvas', { static: false }) canvas: ElementRef;
  @ViewChild('fileinput', { static: false }) fileinput: ElementRef;



  canvasElement: any;
  videoElement: any;
  canvasContext: any;
  scanActive = false;
  scanResult = null;
  loading: HTMLIonLoadingElement = null;
  ficheEmargementActuel: FicheEmargement
  utilisateurCo: string = "patrick"

  constructor(
    private toastCtrl: ToastController,
    private loadingCtrl: LoadingController,
    private ficheEmargementService: FicheEmargementService

  ){

  }

  ngOnInit() {
  }

  ngAfterViewInit() {
    this.canvasElement = this.canvas.nativeElement;
    this.canvasContext = this.canvasElement.getContext('2d');
    this.videoElement = this.video.nativeElement;

  }

  reset() {
    this.scanResult = null;
  }

  stopScan() {
    this.scanActive = false;
  }

  async startScan() {

    const stream = await navigator.mediaDevices.getUserMedia({
      video: { facingMode: 'environment' }
    });

    this.videoElement.srcObject = stream;
    // Required for Safari
    this.videoElement.setAttribute('playsinline', true);

    this.loading = await this.loadingCtrl.create({});
    await this.loading.present();

    this.videoElement.play();
    requestAnimationFrame(this.scan.bind(this));
  }

  async scan() {
    if (this.videoElement.readyState === this.videoElement.HAVE_ENOUGH_DATA) {
      if (this.loading) {
        await this.loading.dismiss();
        this.loading = null;
        this.scanActive = true;
      }

      this.canvasElement.height = this.videoElement.videoHeight;
      this.canvasElement.width = this.videoElement.videoWidth;

      this.canvasContext.drawImage(
        this.videoElement,
        0,
        0,
        this.canvasElement.width,
        this.canvasElement.height
      );
      const imageData = this.canvasContext.getImageData(
        0,
        0,
        this.canvasElement.width,
        this.canvasElement.height
      );
      const code = jsQR(imageData.data, imageData.width, imageData.height, {
        inversionAttempts: 'dontInvert'
      });

      if (code) {
        this.scanActive = false;
        this.scanResult = code.data.toString();
        this.signatureFicheViaQrCode()
      } else {
        if (this.scanActive) {
          requestAnimationFrame(this.scan.bind(this));
        }
      }
    } else {
      requestAnimationFrame(this.scan.bind(this));
    }
  }


  signatureFicheViaQrCode(){
    this.ficheEmargementActuel = this.ficheEmargementService.getFiche(this.scanResult)
    console.log(this.ficheEmargementActuel.nomCours)
    this.ficheEmargementActuel.listeEleves.push(this.utilisateurCo)

  }

}
