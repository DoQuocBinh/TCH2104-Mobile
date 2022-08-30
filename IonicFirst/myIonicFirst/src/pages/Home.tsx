import { IonButton, IonCol, IonContent, IonGrid, IonHeader, IonIcon, IonInput, IonItem, IonLabel, IonPage, IonRow, IonTitle, IonToolbar } from '@ionic/react';
import { calculator, refreshCircleOutline, refreshOutline } from 'ionicons/icons'
import './Home.css';



const Home: React.FC = () => {
  return (
    <IonPage>
      <IonHeader>
        <IonToolbar color="primary">
          <IonTitle>BMI Calculator</IonTitle>
        </IonToolbar>
      </IonHeader>
      <IonContent>
        <IonItem>
          <IonLabel position='floating'>Weight</IonLabel>
          <IonInput></IonInput>
        </IonItem>
        <IonItem>
          <IonLabel position='floating'>Height</IonLabel>
          <IonInput></IonInput>
        </IonItem>
        <IonGrid>
          <IonRow className='ion-text-center'>
            <IonCol><IonButton color="primary">
              <IonIcon icon={calculator} slot="start"></IonIcon>
              Calculate</IonButton></IonCol>
            <IonCol >            
              <IonButton fill='outline'>
                <IonIcon icon={refreshOutline} slot="start"></IonIcon>
                Clear</IonButton></IonCol>
          </IonRow>
        </IonGrid>
      </IonContent>
    </IonPage>
  );
};

export default Home;
