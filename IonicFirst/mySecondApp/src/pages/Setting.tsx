import { IonButton, IonContent, IonDatetime, IonHeader, IonInput, IonItem, IonLabel, IonList, IonPage, IonPopover, IonTitle, IonToolbar } from '@ionic/react';
import { useState } from 'react';

const Setting: React.FC = () => {
  return (
    <IonPage>
      <IonHeader>
        <IonToolbar color="warning">
          <IonTitle>Product management</IonTitle>
        </IonToolbar>
      </IonHeader>
      <IonContent>
        <h1>Settings</h1>
      </IonContent>
    </IonPage>
  );
};

export default Setting;
