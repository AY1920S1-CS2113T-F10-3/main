package command;

import common.AlphaNUSException;
import common.TaskList;
import payment.Payee;
import payment.PaymentManager;
import payment.Payments;
import project.Fund;
import project.Project;
import task.Deadline;
import task.DoAfterTasks;
import task.Task;
import task.WithinPeriodTask;
import ui.Ui;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
/**
 * Parser that parses input from the user.
 */
public class Parser {
    private static Instruction instr = new Instruction();
    private static Process process = new Process();


    /**
     * Method that parses input from the user and executes processes based on the input.
     * @param input Input from the user.
     * @param tasklist Tasklist of the user.
     * @param ui Ui that interacts with the user.
     * @param storage Storage for the Tasklist.
     * @param commandList List of input commands.
     * @return Returns boolean variable to indicate when to stop parsing for input.
     * @throws AlphaNUSException if input is not valid.
     */

    public static boolean parse(String input, TaskList tasklist, Ui ui, Fund fund, Storage storage, ArrayList<String> commandList) {
        try {
            if (instr.isBye(input)) {
                //print bye message
                ui.byeMessage();
                ui.getIn().close();
                return true;
            } else if (instr.isViewhistory(input)) {
                process.viewhistory(input, ui, commandList, storage);
            } else if (instr.isHistory(input)) {
                process.history(ui,commandList, storage);
            } else if (instr.isListProjects(input)){
                process.listProjects(ui);
            } else if (instr.isAddProject(input)) {
                process.commandHistory(input, ui, storage);
                process.addProject(input, ui);
            } else if (instr.isDeleteProject(input)) {
                process.deleteProject(input, ui);
                process.commandHistory(input, ui, storage);
            } else if (instr.isGoToProject(input)) {
                process.goToProject(input, ui);
                process.commandHistory(input, ui, storage);
            } else if (instr.isList(input)) {
                ui.printList(tasklist, "list");
                process.commandHistory(input, ui, storage);
            } else if (instr.isDone(input)) {
                process.done(input, tasklist, ui);
                process.commandHistory(input, ui, storage);
            } else if (instr.isDeadline(input)) {
                process.deadline(input, tasklist, ui);
                process.commandHistory(input, ui, storage);
                //storage.save(tasklist.returnArrayList());

            } else if (instr.isDoAfter(input)) {
                process.doAfter(input, tasklist, ui);
                //Storage.save(tasklist.returnArrayList());
            } else if (instr.isDeletePayment(input)) {
                process.deletePayment(input, ui);
                process.commandHistory(input, ui, storage);
                //storage.save(tasklist.returnArrayList());

            } else if (instr.isFind(input)) {
                // process.find(input, tasklist, ui);
                process.commandHistory(input, ui, storage);
            } else if (instr.isWithinPeriodTask(input)) {
                process.within(input, tasklist, ui);
                process.commandHistory(input, ui, storage);
                //storage.save(tasklist.returnArrayList());
            } else if (instr.isSnooze(input)) {
                process.snooze(input, tasklist, ui);
                process.commandHistory(input, ui, storage);
                //storage.save(tasklist.returnArrayList());
            /*
            `} else if (instr.isPostpone(input)) {
                process.postpone(input, tasklist, ui);
                storage.save(tasklist.returnArrayList());`
            */
            } else if (instr.isReschedule(input)) {
                // process.reschedule(input, tasklist, ui);
                //storage.save(tasklist.returnArrayList());
            } else if (instr.isViewSchedule(input)) {
                process.viewSchedule(input, tasklist, ui);
                process.commandHistory(input, ui, storage);
                //storage.save(tasklist.returnArrayList());
            } else if (instr.isReminder(input)) {
                //process.reminder(input, tasklist, ui);
                process.commandHistory(input, ui, storage);
            } else if (instr.isEdit(input)) {
                process.edit(input,ui);
                process.commandHistory(input, ui, storage);
            } else if (instr.isAddPayment(input)) {
                process.addPayment(input, ui);
                process.commandHistory(input, ui, storage);
            } else if (instr.isgetpayee(input)) {
                process.findPayee(input, ui);
                process.commandHistory(input, ui, storage);
            } else if (instr.isAddPayee(input)) {
                process.addPayee(input, ui);
                process.commandHistory(input, ui, storage);
            } else if (instr.isDeletePayee(input)) {
                process.deletePayee(input, ui);
                process.commandHistory(input, ui, storage);
            } else if (instr.isInvoice(input)) {
                process.inVoice(input, tasklist, ui);
                process.commandHistory(input, ui, storage);
            } else if (instr.isHistory(input)) {
                process.commandHistory(input, ui, storage);
            } else if (instr.isSetFund(input)) {
                process.setFund(input, ui, fund);
                process.commandHistory(input, ui, storage);
            } else if (instr.isAddFund(input)) {
                process.addFund(input, ui, fund);
                process.commandHistory(input, ui, storage);
            } else if (instr.isAssignFund(input)) {
                process.assignFund(input, ui, fund);
                process.commandHistory(input, ui, storage);
            } else if (instr.isHelp(input)) {
                ui.printHelpMessage();
            } else {
                throw new AlphaNUSException("\t" + "OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (AlphaNUSException e) {
            ui.exceptionMessage(e.getMessage());
        } catch (NullPointerException e) {
            ui.exceptionMessage("NULLPOINTEREXCEPTION");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }
}
    
