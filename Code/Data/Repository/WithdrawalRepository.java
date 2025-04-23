package Data.Repository;

import Data.Models.Application;
import Data.Models.Model;
import Data.Models.Withdrawal;

import java.util.ArrayList;

import static Util.Config.*;

public class WithdrawalRepository extends DataRepository {
    private static WithdrawalRepository instance;

    public WithdrawalRepository() {
        setFilepath(DATA_PATH + WITHDRAWAL_CSV);
        fetch();
    }

    @Override
    protected ArrayList<Model> toModelList(ArrayList<ArrayList<String>> csv) {
        ArrayList<Model> withdrawalArr = new ArrayList<>();

        for (ArrayList<String> strArr : csv) {
            withdrawalArr.add(new Withdrawal(
                    strArr.get(0),
                    strArr.get(1)
                    )
            );
        }

        return withdrawalArr;
    }

    @Override
    protected ArrayList<ArrayList<String>> toCSV(ArrayList<Model> alm) {
        ArrayList<ArrayList<String>> csvData = new ArrayList<>();

        alm.forEach(model -> {
            Withdrawal application = (Withdrawal) model;

            ArrayList<String> row = new ArrayList<>();
            row.add(application.getID());
            row.add(application.getStatus());

            csvData.add(row);
        });

        return csvData;
    }

    public static WithdrawalRepository getInstance() {
        if (instance == null)
            instance = new WithdrawalRepository();
        return instance;
    }

}
